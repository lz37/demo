<template>
	<el-button type="primary">新增作业</el-button>
  <el-table :data="homeworks" style="width: 100%">
    <el-table-column type="expand">
      <template #default="props">
        <el-table :data="props.row.students" style="width: 100%">
          <el-table-column label="学号" prop="id" />
          <el-table-column label="姓名" prop="name" />
          <el-table-column label="上传时间" prop="update" />
          <el-table-column label="得分" prop="score" />
          <el-table-column label="操作">
            <el-button type="primary">查看</el-button>
          </el-table-column>
        </el-table>
      </template>
    </el-table-column>
    <el-table-column label="作业标题" prop="title" />
    <el-table-column label="上传时间" prop="uploadTime" />
    <el-table-column label="截止时间" prop="deadline" />
    <el-table-column label="平均分数" prop="averageScore" />
  </el-table>
</template>

<script>
export default {
  name: "CourseManagerHomework",
  data() {
    return {
      homeworks: [
        {
          title: "隶书 第一次作业",
          uploadTime: "2020-01-01",
          deadline: "2020-01-02",
          averageScore: 90,
          students: [
            {
              id: "111",
              name: "奥巴马",
              update: "2020-01-01",
              score: 95,
            },
            {
              id: "222",
              name: "特朗普",
              update: "2020-01-01",
              score: 90,
            },
            {
              id: "333",
              name: "比尔盖茨",
              update: "2020-01-02",
              score: 85,
            },
            {
              id: "444",
              name: "乔布斯",
              update: "2020-01-02",
              score: NaN,
            },
          ],
        },
				{
          title: "草书 第二次作业",
          uploadTime: "2020-01-03",
          deadline: "2020-01-04",
          averageScore: 90,
          students: [
            {
              id: "101",
              name: "马化腾",
              update: "2020-01-03",
              score: 95,
            },
            {
              id: "202",
              name: "马云",
              update: "2020-01-03",
              score: 90,
            },
            {
              id: "303",
              name: "马保国",
              update: "2020-01-04",
              score: 85,
            },
          ],
        },
      ],
    };
  },
};
</script>

<style></style>
