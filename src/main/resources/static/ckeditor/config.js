/**
 * @license Copyright (c) 2003-2018, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function (config) {
    // Define changes to default configuration here. For example:
    // config.language = 'fr';
    // config.uiColor = '#AADC6E';
    //config.filebrowserImageUploadUrl = '/upload';
    config.filebrowserImageBrowseUrl = '/ckfinder/ckfinder.html?type=Images';//使用ckfinder
    config.image_previewText = '';     //预览区域显示内容
};
