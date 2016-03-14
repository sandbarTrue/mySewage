package com.cqupt.project.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cqupt.project.model.Attachment;
import com.cqupt.project.model.ReturnStatus;
import com.cqupt.project.service.AttachmentService;
import com.cqupt.project.utils.JSONUtils;
@Controller("attachmentController")
@RequestMapping("/attachment")
public class AttachmentController {
	@Resource
	private AttachmentService attachmentService;
    @Resource
    private  UserController userController;
	@RequestMapping("/addAttachment")
	public void addAttachment(HttpServletResponse response,
			CommonsMultipartFile[] files, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String FileId = UUID.randomUUID().toString();
		String realPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "upload";
		boolean result = attachmentService.addAttachment(files, FileId,
				realPath);
		if (result) {
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "成功");
			map.put("data", null);
		} else {
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "失败");
			map.put("data", null);

		}
		JSONUtils.toJSON(map, response);
	}

	@RequestMapping("/delAttachment")
	public void delAttachment(HttpServletResponse response, String fileId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Attachment> attachments = attachmentService
				.LoadAttachments(fileId);
		for (Attachment a : attachments) {
			File file = new File(a.getFilePath() + a.getUploadFileName());
			System.out.println("dalete" + file.getName());
			file.delete();
		}
		boolean result = attachmentService.deleteAttachemnt(fileId);
		if (result) {
			map.put("status", ReturnStatus.SUCCSS);
			map.put("msg", "成功");
			map.put("data", null);
		} else {
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "失败");
			map.put("data", null);
		}
		JSONUtils.toJSON(map, response);
	}

	@RequestMapping("/downloadAttachment")
	public  void download(@RequestParam("realName") String realName,
			@RequestParam("fileName") String fileName,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean result=userController.islogin(request);
		if(!result ){
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "没有登录");
			map.put("data", null);
			JSONUtils.toJSON(map, response);
		}
		else{
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			// 获取项目根目录
			String realPath = request.getSession().getServletContext()
					.getRealPath("/");
			// 获取下载文件露肩
			String downLoadPath = realPath + "upload/" + fileName;
			// 获取文件的长度
			long fileLength = new File(downLoadPath).length();
			// 设置文件输出类型
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment; filename="
					+ realName);
			// 设置输出长度
			response.setHeader("Content-Length", String.valueOf(fileLength));
			// 获取输入流
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			// 输出流
		    bos=new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			// 关闭流
		
			bis.close();
			bos.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "失败");
			map.put("data", null);
			e.printStackTrace();
			JSONUtils.toJSON(map, response);
		}
		
		}
		
	}

	// 批量打包下载
	@RequestMapping("/downloadZip")
	public void downloadFiles(@RequestParam("fileId") String fileId,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean result=userController.islogin(request);
		Map<String, Object> map = new HashMap<String, Object>();
		List<File> files = new ArrayList<File>();
		List<Attachment> attachments = new ArrayList<Attachment>();
		// fileId需要批量下载文件id
		if(!result){
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "没有登录");
			map.put("data", null);
			JSONUtils.toJSON(map, response);
		}
		else if ("" == fileId || null == fileId) {
			System.out.println("下载的文件不存在");
			map.put("status", ReturnStatus.ERROR);
			map.put("msg", "失败");
			map.put("data", null);
			JSONUtils.toJSON(map, response);
		} else {
			try {
				attachments = attachmentService.LoadAttachments(fileId);
				for (Attachment a : attachments) {
					File oldFile = new File(a.getFilePath()
							+ a.getUploadFileName());
					File realFile = new File(a.getFilePath()
							+ a.getUploadRealName());
					oldFile.renameTo(realFile);
					
					files.add(realFile);
				}

				String fileName = UUID.randomUUID().toString() + ".zip";
				// 在服务器端创建打包下载的临时文件
				String outFilePath = request.getSession().getServletContext()
						.getRealPath("/")
						+ "uploadFile/";
				createFile(outFilePath, fileName);
				File file = new File(outFilePath + fileName);
				// 文件输出流
				FileOutputStream outStream = new FileOutputStream(file);
				// 压缩流
				ZipOutputStream toClient = new ZipOutputStream(outStream);

				zipFile(files, toClient);
				toClient.close();
				outStream.close();
				this.downloadFile(file, response, true);
				for (Attachment a : attachments) {
					File oldFile = new File(a.getFilePath()
							+ a.getUploadRealName());
					File realFile = new File(a.getFilePath()
							+ a.getUploadFileName());
					oldFile.renameTo(realFile);
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				map.put("status", ReturnStatus.ERROR);
				map.put("msg", "失败");
				map.put("data", null);
				e.printStackTrace();
				JSONUtils.toJSON(map, response);
			}
			
		}
		
	}

	// 创建文件
	public void createFile(String path, String fileName) {
		// path表示你所创建文件的路径
		File f = new File(path);
		if (!f.isDirectory()) {
			f.mkdir();
		}
		File file = new File(f, fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void zipFile(List<File> files, ZipOutputStream outputStream)
			throws IOException, ServletException {
		try {
			int size = files.size();
			// 压缩列表中的文件
			for (int i = 0; i < size; i++) {
				File file = (File) files.get(i);
				zipFile(file, outputStream);
			}
		} catch (IOException e) {
			throw e;
		}
	}

	public static void zipFile(File inputFile, ZipOutputStream outputstream)
			throws IOException, ServletException {
		try {
			if (inputFile.exists()) {
				if (inputFile.isFile()) {
					FileInputStream inStream = new FileInputStream(inputFile);
					BufferedInputStream bInStream = new BufferedInputStream(
							inStream);
					ZipEntry entry = new ZipEntry(inputFile.getName());
					outputstream.putNextEntry(entry);
					final int MAX_BYTE = 10 * 1024 * 1024; // 最大的流为10M
					long streamTotal = 0; // 接受流的容量
					int streamNum = 0; // 流需要分开的数量
					int leaveByte = 0; // 文件剩下的字符数
					byte[] inOutbyte; // byte数组接受文件的数据
					streamTotal = bInStream.available(); // 通过available方法取得流的最大字符数
					streamNum = (int) Math.floor(streamTotal / MAX_BYTE); // 取得流文件需要分开的数量
					leaveByte = (int) streamTotal % MAX_BYTE; // 分开文件之后,剩余的数量
					if (streamNum > 0) {
						for (int j = 0; j < streamNum; ++j) {
							inOutbyte = new byte[MAX_BYTE];
							// 读入流,保存在byte数组
							bInStream.read(inOutbyte, 0, MAX_BYTE);
							outputstream.write(inOutbyte, 0, MAX_BYTE); // 写出流
						}
					}
					// 写出剩下的流数据
					inOutbyte = new byte[leaveByte];
					bInStream.read(inOutbyte, 0, leaveByte);
					outputstream.write(inOutbyte);
					outputstream.closeEntry(); // Closes the current ZIP entry
					// and positions the stream for
					// writing the next entry
					bInStream.close(); // 关闭
					inStream.close();
				}
			} else {
				throw new ServletException("文件不存在！");
			}
		} catch (IOException e) {
			throw e;
		}
	}

	public void downloadFile(File file, HttpServletResponse response,
			boolean isDelete) throws IOException {
		OutputStream toClient=null;
		try {
			// 以流的形式下载文件。
			BufferedInputStream fis = new BufferedInputStream(
					new FileInputStream(file.getPath()));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			 toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename="
							+ new String(file.getName().getBytes("UTF-8"),
									"ISO-8859-1"));
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
			if (isDelete) {
				file.delete(); // 是否将生成的服务器端文件删除
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
