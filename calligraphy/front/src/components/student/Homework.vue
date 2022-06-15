<template>
  <el-space wrap>
    <el-card
      v-for="homework in homeworks"
      :key="homework"
      class="box-card"
      style="width: 250px"
    >
      <template #header>
        <div class="card-header">
          <span>
            <h1>{{ homework.name }}</h1>
          </span>
          <el-button
            class="button"
            text
            @click="this.$router.push('/copybook')"
            >查看</el-button
          >
        </div>
      </template>
      <div>提交情况：{{ homework.status }}</div>
      <div>所属学校：{{ homework.school }}</div>
      <div>所属班级：{{ homework.class }}</div>
      <div>任课老师：{{ homework.teacher }}</div>
      <div>作业评分：{{ homework.scores }}</div>
    </el-card>
  </el-space>
</template>

<script>
export default {
  name: "HomeworkComponent",
  data() {
    return {
      homeworks: [
        {
          status: "未提交",
          school: "牛马小学",
          class: "一年级一班",
          teacher: "张三",
          scores: "--",
          name: "隶书 第一次作业",
        },
      ],
    };
  },
};
</script>

<style></style>
