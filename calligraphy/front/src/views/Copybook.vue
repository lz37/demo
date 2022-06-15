<template>
  <div class="file-container">
    <el-upload
      class="upload-demo"
      drag
      action="https://jsonplaceholder.typicode.com/posts/"
      multiple
      :on-error="onError"
    >
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
    </el-upload>
    <div class="img-container">
      <img :src="copyBook" id="img-sample" />
    </div>
  </div>
  <el-dialog
    v-model="dialogVisible"
    title="Tips"
    width="30%"
    :before-close="handleClose"
  >
    <span>您本次的评分：90</span><br />
    <span>错字：牛（1）、马（2）</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogVisible = false"
          >确认</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script>
import copyBook from "../assets/copybook.jpeg";
export default {
  name: "CopybookView",
  data() {
    return {
      copyBook: copyBook,
      fileList: [],
      dialogVisible: false,
    };
  },
  methods: {
    onError(err, file, fileList) {
      this.dialogVisible = true;
    },
  },
};
</script>

<style>
.img-container {
  width: 100%;
  height: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
}
#img-sample {
  height: 100%;
}
</style>
