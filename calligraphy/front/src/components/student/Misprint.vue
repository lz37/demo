<template>
  <el-table :data="misprints" style="width: 100%">
    <el-table-column prop="character" label="汉字" />
    <el-table-column prop="times" label="错误字数" />
    <el-table-column label="操作">
      <el-button size="small">练习</el-button>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "MisprintComponent",
  data() {
    return {
      misprints: [
        {
          character: "牛",
          times: "2",
        },
        {
          character: "马",
          times: "10",
        }
      ]
    }
  },
};
</script>

<style></style>
