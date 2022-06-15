<template>
  <el-button v-if="isAdmin" type="primary">发布公告</el-button>
  <el-table :data="messages" style="width: 100%">
    <el-table-column prop="title" label="标题" width="180" />
    <el-table-column prop="content" label="内容" />
    <el-table-column prop="time" label="发布时间" width="180" />
  </el-table>
</template>

<script>
export default {
  name: "AnnouncementComponent",
  data() {
    return {
      isAdmin: window.localStorage.getItem("identity") == "admin",
      messages: [
        {
          title: "HelloWorld",
          content: "HelloWorld",
          time: "2020-01-01",
        },
        {
          title: "一日三餐没烦恼",
          content: "一日三餐没烦恼",
          time: "2020-01-01",
        },
        {
          title: "就吃老八秘制小汉堡儿",
          content: "就吃老八秘制小汉堡儿",
          time: "2020-01-01",
        },
      ],
    };
  },
};
</script>

<style></style>
