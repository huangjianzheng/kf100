<template>
  <div class="editor-panel">
    <div ref="toolbar" class="toolbar"></div>
    <div ref="editor" class="editor"></div>
  </div>
</template>

<script>
  import E from "wangeditor";
  export default {
    props: {
      html: {
        type: String
      }
    },
    data(){
      return{
        editor2:null,
      }
    },
    mounted() {
      let editor2 = new E(this.$refs.toolbar, this.$refs.editor);
      editor2.customConfig.onchange = html => {
        this.$emit("change", html);
         window.localStorage.setItem("preview_html",html)
      };

      editor2.customConfig.uploadImgShowBase64 = true;
      editor2.customConfig.showLinkImg = true;
      editor2.customConfig.zIndex = 100;
      // 关闭粘贴样式的过滤
      editor2.customConfig.pasteFilterStyle = false;
      // 忽略粘贴内容中的图片
      editor2.customConfig.pasteIgnoreImg = false;
      // 自定义菜单配置
      editor2.customConfig.menus = [
        "undo", // 撤销
        "redo", // 重复
        "head", // 标题
        "bold", // 粗体
        "fontSize", // 字号
        "fontName", // 字体
        "italic", // 斜体
        "underline", // 下划线
        "strikeThrough", // 删除线
        "foreColor", // 文字颜色
        "backColor", // 背景颜色
        "link", // 插入链接
        "list", // 列表
        "justify", // 对齐方式
        "quote", // 引用
        //   "emoticon", // 表情
        "image",// 插入图片
        "table", // 表格
        //   "video", // 插入视频
        //    "code", // 插入代码
      ];
      editor2.create();
      this.editor2 = editor2;
      this.initFullScreenBtn(".editor-panel");
      this.initPreviewBtn(".editor-panel");
      if (this.html) {
        editor2.txt.html(this.html);
      }
    },
    methods: {
      initFullScreenBtn(editorSelector) {
        $(editorSelector + " .w-e-toolbar").append('<div class="w-e-menu"><a id="btn_fullScreen" class="_wangEditor_btn_fullScreen">全屏</a></div>');
        this.$nextTick(_ => {
          let btn = document.getElementById('btn_fullScreen');
          btn.addEventListener('click', function () {
            $(editorSelector).toggleClass('fullScreen-editor');
            if ($(editorSelector + ' ._wangEditor_btn_fullScreen').text() === '全屏') {
              $(editorSelector + ' ._wangEditor_btn_fullScreen').text('退出全屏');
            } else {
              $(editorSelector + ' ._wangEditor_btn_fullScreen').text('全屏');
            }
          }, false)
        });
      },
      initPreviewBtn(editorSelector) {
        $(editorSelector + " .w-e-toolbar").append('<div class="w-e-menu"><a id="btn_preview" class="_wangEditor_btn_preview">预览</a></div>');
        this.$nextTick(_ => {
          let _this = this;
          let btn = document.getElementById('btn_preview');
          btn.addEventListener('click', function () {
            let timestamp = Date.parse(new Date());
            window.open("/#/preview?id="+timestamp,"preview");
          }, false)
        });
      },
      clear(){
        if(this.editor2){
          this.editor2.txt.clear();
        }
      },
      setHtml(html){
        if(this.editor2){
          this.editor2.txt.html(html);
        }
      }
    }
  };
</script>

<style scoped>

  .toolbar {
    background-color: #f1f1f1;
    border: 1px solid #ccc;
  }

  .editor {
    border: 1px solid #ccc;
    border-top: none;
    height: 280px;
    z-index: 10000;
  }



  .fullScreen-editor {
    position: fixed !important;
    padding-top: 20px;
    padding-left: 500px;
    padding-right: 500px;
    padding-bottom: 50px;
    width: 100% !important;
    height: 100% !important;
    left: 0px !important;
    top: 0px !important;
    background-color: white;
    z-index: 9999;
  }

  .fullScreen-editor .w-e-text-container {
    width: 100% !important;
    height: 100% !important;
  }
</style>
