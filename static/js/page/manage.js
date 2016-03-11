$(document).ready(function(){
    // Full featured editor
    $('#editor').each(function(index, element)
    {
        $(element).wysiwyg({
            classes: 'some-more-classes',
            // 'selection'|'top'|'top-selection'|'bottom'|'bottom-selection'
            toolbar: index == 0 ? 'top-selection' : (index == 1 ? 'bottom' : 'selection'),
            buttons: {
                // Dummy-HTML-Plugin
                dummybutton1: index != 1 ? false : {
                    html: $('<input id="submit" type="button" value="bold" />').click(function(){
                                // We simply make 'bold'
                                if( $(element).wysiwyg('shell').getSelectedHTML() )
                                    $(element).wysiwyg('shell').bold();
                                else
                                    alert( 'Please selection some text' );
                            }),
                    //showstatic: true,    // wanted on the toolbar
                    showselection: false    // wanted on selection
                },
                bold: {
                    title: '加粗 (Ctrl+B)',
                    image: '\uf032', 
                    hotkey: 'b'
                },
                italic: {
                    title: '倾斜 (Ctrl+I)',
                    image: '\uf033', 
                    hotkey: 'i'
                },
                underline: {
                    title: '下划线 (Ctrl+U)',
                    image: '\uf0cd', 
                    hotkey: 'u'
                },
                // Fontname plugin
                fontname: index == 1 ? false : {
                    title: '字体',
                    image: '\uf031', 
                    popup: function( $popup, $button ) {
                            var list_fontnames = {
                                    // Name : Font
                                    'Arial, Helvetica' : 'Arial,Helvetica',
                                    'Consolas'         : 'Consolas',
                                    'Courier New'      : 'Courier New,Courier',
                                    'Georgia'          : 'Georgia',
                                    'Source Code Pro'  : 'Source Code Pro',
                                    'Times New Roman'  : 'Times New Roman,Times',
                                    'Verdana'          : 'Verdana,Geneva',
                                    '仿宋GB-2312'      : '仿宋GB-2312',
                                    '宋体'             : '宋体',
                                    '微软雅黑'         : 'Microsoft Yahei',
                                };
                            var $list = $('<div/>').addClass('wysiwyg-toolbar-list')
                                                   .attr('unselectable','on');
                            $.each( list_fontnames, function( name, font ){
                                var $link = $('<a/>').attr('href','#')
                                                    .css( 'font-family', font )
                                                    .html( name )
                                                    .click(function(event){
                                                        $(element).wysiwyg('shell').fontName(font).closePopup();
                                                        // prevent link-href-#
                                                        event.stopPropagation();
                                                        event.preventDefault();
                                                        return false;
                                                    });
                                $list.append( $link );
                            });
                            $popup.append( $list );
                           },
                    //showstatic: true,    // wanted on the toolbar
                    showselection: index == 0 ? true : false    // wanted on selection
                },
                // Fontsize plugin
                fontsize: index == 1 ? false : {
                    title: '字体大小',
                    image: '\uf034', 
                    popup: function( $popup, $button ) {
                            var list_fontsizes = {  
                                // Name : Size
                                '一号'    : 7,
                                '二号'  : 6,
                                '三号'   : 5,
                                '四号'  : 4,
                                '五号'   : 3,
                                '六号' : 2,
                                '七号'    : 1
                            };
                            var $list = $('<div/>').addClass('wysiwyg-toolbar-list')
                                                   .attr('unselectable','on');
                            $.each( list_fontsizes, function( name, size ){
                                var $link = $('<a/>').attr('href','#')
                                                    .css( 'font-size', (8 + (size * 3)) + 'px' )
                                                    .html( name )
                                                    .click(function(event){
                                                        $(element).wysiwyg('shell').fontSize(size).closePopup();
                                                        // prevent link-href-#
                                                        event.stopPropagation();
                                                        event.preventDefault();
                                                        return false;
                                                    });
                                $list.append( $link );
                            });
                            $popup.append( $list );
                           }
                    //showstatic: true,    // wanted on the toolbar
                    //showselection: true    // wanted on selection
                },
                alignleft: index != 0 ? false : {
                    title: '左对齐',
                    image: '\uf036', 
                    //showstatic: true,    // wanted on the toolbar
                    showselection: false    // wanted on selection
                },
                aligncenter: index != 0 ? false : {
                    title: '居中',
                    image: '\uf037', 
                    //showstatic: true//   // wanted on the toolbar
                    showselection: false    // wanted on selection
                },
                alignright: index != 0 ? false : {
                    title: '右对齐',
                    image: '\uf038', 
                    //showstatic: true,    // wanted on the toolbar
                    showselection: false    // wanted on selection
                },
                indent: index != 0 ? false : {
                    title: '缩进',
                    image: '\uf03c', 
                    //showstatic: true,    // wanted on the toolbar
                    showselection: false    // wanted on selection
                },
                outdent: index != 0 ? false : {
                    title: '取消缩进',
                    image: '\uf03b', 
                    //showstatic: true,    // wanted on the toolbar
                    showselection: false    // wanted on selection
                },
                orderedList: index != 0 ? false : {
                    title: '有序列表',
                    image: '\uf0cb', 
                    //showstatic: true,    // wanted on the toolbar
                    showselection: false    // wanted on selection
                },
                unorderedList: index != 0 ? false : {
                    title: '无序列表',
                    image: '\uf0ca', 
                    //showstatic: true,    // wanted on the toolbar
                    showselection: false    // wanted on selection
                },
                removeformat: {
                    title: '清除样式',
                    image: '\uf12d' 
                }
            },
            // Submit-Button
            submit: {
                title: 'Submit',
                image: '\uf00c' 
            },
            // Other properties
            dropfileclick: 'Drop image or click',
            placeholderUrl: 'www.example.com',
            maxImageSize: [600,200]
            /*
            onImageUpload: function( insert_image ) {
                            // Used to insert an image without XMLHttpRequest 2
                            // A bit tricky, because we can't easily upload a file
                            // via '$.ajax()' on a legacy browser.
                            // You have to submit the form into to a '<iframe/>' element.
                            // Call 'insert_image(url)' as soon as the file is online
                            // and the URL is available.
                            // Best way to do: http://malsup.com/jquery/form/
                            // For example:
                            //$(this).parents('form')
                            //       .attr('action','/path/to/file')
                            //       .attr('method','POST')
                            //       .attr('enctype','multipart/form-data')
                            //       .ajaxSubmit({
                            //          success: function(xhrdata,textStatus,jqXHR){
                            //            var image_url = xhrdata;
                            //            console.log( 'URL: ' + image_url );
                            //            insert_image( image_url );
                            //          }
                            //        });
                        },
            onKeyEnter: function() {
                            return false; // swallow enter
                        }
            */
        })
        .change(function(){
            if( typeof console != 'undefined' )
                console.log( 'change' );
        })
        .focus(function(){
            if( typeof console != 'undefined' )
                console.log( 'focus' );
        })
        .blur(function(){
            if( typeof console != 'undefined' )
                console.log( 'blur' );
        });
    });
    var wysiwygeditor = wysiwyg( option );
    //wysiwygeditor.setHTML( '<html>' );
});
