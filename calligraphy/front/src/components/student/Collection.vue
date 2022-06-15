<template>
  <div class="course">
    <h1 class="collection-title">收藏课程</h1>
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
            <el-button
              class="button"
              text
              @click="this.$router.push('/course/' + course.id)"
              >进入</el-button
            >
          </div>
        </template>
        <div>课程编号：{{ course.id }}</div>
        <div>授课老师：{{ course.teacher }}</div>
        <div>开课学校：{{ course.school }}</div>
        <div>开课班级：{{ course.class }}</div>
      </el-card>
    </el-space>
  </div>
  <div class="copybook">
    <h1 class="collection-title">收藏字帖</h1>
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
  </div>
</template>

<script>
export default {
  name: "CollectionComponent",
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
      copybooks: [
        {
          status: "已完成",
          name: "隶书 第一章",
          difficulty: "简单",
          scores: "80",
        },
        {
          status: "已完成",
          name: "隶书 第二章",
          difficulty: "简单",
          scores: "95",
        },
        {
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

<style>
.collection-title {
  text-align: center;
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 20px;
}
.copybook {
  margin-top: 20px;
}
</style>
