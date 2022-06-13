<template>
  <el-space wrap>
    <el-card
      v-for="course in courses"
      :key="course"
      class="box-card"
      style="width: 250px"
    >
      <template #header>
        <div class="card-header">
          <span>
            <h1>{{ course.name }}</h1>
          </span>
          <el-button class="button" text>进入</el-button>
        </div>
      </template>
      <div>课程编号：{{ course.id }}</div>
      <div>授课老师：{{ course.teacher }}</div>
      <div>开课学校：{{ course.school }}</div>
      <div>开课班级：{{ course.class }}</div>
    </el-card>
  </el-space>
</template>

<script>
export default {
  name: "CourseComponent",
  data() {
    return {
      courses: [
        {
          id: 1123,
          name: "隶书",
          teacher: "张三",
          school: "牛马小学",
          class: "一年级一班",
        },
        {
          id: 114514,
          name: "楷书",
          teacher: "李四",
          school: "逆天小学",
          class: "三年级一班",
        },
      ],
    };
  },
};
</script>

<style>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
