<template>
  <el-button v-if="isAdmin" type="primary">新增字帖</el-button>
  <el-table :data="copybooks" style="width: 100%">
    <el-table-column prop="status" label="状态" />
    <el-table-column prop="name" label="名称" width="180" />
    <el-table-column prop="difficulty" label="难度" />
    <el-table-column prop="scores" label="得分" />
    <el-table-column label="操作">
      <el-button size="small" @click="this.$router.push('/copybook')"
        >查看</el-button
      >
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "CopybookComponent",
  data() {
    return {
      isAdmin: window.localStorage.getItem("identity") == "admin",
      copybooks: [
        {
          id: 1,
          status: "未完成",
          name: "楷书 第一章",
          difficulty: "简单",
          scores: "--",
        },
        {
          id: 2,
          status: "未完成",
          name: "楷书 第二章",
          difficulty: "简单",
          scores: "--",
        },
        {
          id: 3,
          status: "未完成",
          name: "楷书 第三章",
          difficulty: "中等",
          scores: "--",
        },
        {
          id: 4,
          status: "已完成",
          name: "隶书 第一章",
          difficulty: "简单",
          scores: "80",
        },
        {
          id: 5,
          status: "已完成",
          name: "隶书 第二章",
          difficulty: "简单",
          scores: "95",
        },
        {
          id: 6,
          status: "未完成",
          name: "草书 第一章",
          difficulty: "困难",
          scores: "--",
        },
      ],
    };
  },
};
</script>

<style></style>
