<template>
  <div>
    <h1 style="text-align: center">课程预览</h1>
    <div class="video-component-container">
      <VideoComponent></VideoComponent>
    </div>
    <div>
      <el-row class="mb-4">
        <h2>修改当前课程信息</h2>
        <el-button type="primary" @click="this.fileVisible = true"
          >修改</el-button
        >
      </el-row>
    </div>
    <el-upload
      class="upload-demo"
      drag
      action="https://jsonplaceholder.typicode.com/posts/"
      multiple
      :on-error="onError"
      v-if="fileVisible"
    >
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
    </el-upload>
  </div>
  <el-dialog
    v-model="dialogVisible"
    title="Info"
    width="30%"
    :before-close="handleClose"
  >
    <span>课程修改成功</span>
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
import VideoComponent from "../etc/Video.vue";
export default {
  name: "CourseManagerInnerComponent",
  components: {
    VideoComponent,
  },
  data() {
    return {
      dialogVisible: false,
      fileVisible: false,
    };
  },
};
</script>

<style></style>
