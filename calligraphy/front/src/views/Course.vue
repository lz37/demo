<template>
  <el-container
    ><el-aside width="250px">
      <el-scrollbar>
        <el-menu>
          <el-sub-menu index="1-1">
            <template #title>第一章</template>
            <el-menu-item index="1-1-1">第一节</el-menu-item>
            <el-menu-item index="1-1-2">第二节</el-menu-item>
            <el-menu-item index="1-1-3">第三节</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="1-2">
            <template #title>第二章</template>
            <el-menu-item index="1-2-1">第一节</el-menu-item>
            <el-menu-item index="1-2-2">第二节</el-menu-item>
            <el-menu-item index="1-3-3">第三节</el-menu-item>
          </el-sub-menu>
        </el-menu>
      </el-scrollbar>
    </el-aside>
    <el-main>
      <h1 style="text-align: center">测试标题</h1>
      <div class="video-component-container">
        <VideoComponent></VideoComponent>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import VideoComponent from "../components/etc/Video";
export default {
  components: { VideoComponent },
  name: "CourseView",
};
</script>

<style>
.video-component-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 70%;
}
</style>
