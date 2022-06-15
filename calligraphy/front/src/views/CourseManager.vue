<template>
  <el-container
    ><el-aside width="250px">
      <el-scrollbar>
        <el-menu>
          <el-sub-menu index="1">
            <template #title>
              <span>章节管理</span>
            </template>
            <div class="up-and-down">
              <el-button circle size="small" @click="up()"
                ><el-icon><Top /></el-icon
              ></el-button>
              <el-button circle size="small" @click="bottom()"
                ><el-icon><Bottom /></el-icon
              ></el-button>
            </div>
            <el-sub-menu
              v-for="(chapter, chapterIndex) in chapters"
              :key="chapterIndex"
              :index="'1-' + chapterIndex"
              @click="changeSelected(chapterIndex, NaN)"
            >
              <template #title>
                <el-button
                  style="color: red"
                  :key="danger"
                  :type="danger"
                  link
                  @click="deleteChapter(chapterIndex)"
                  ><el-icon><Delete /></el-icon
                ></el-button>
                <span>{{ chapter.name }}</span>
              </template>
              <el-menu-item
                v-for="(unit, unitIndex) in chapter.units"
                :key="unitIndex"
                :index="'1-' + chapterIndex + '-' + unitIndex"
                @click="
                  changeSelected(chapterIndex, unitIndex);
                  this.$router.push(
                    `/course-manager/${this.$route.params.courseId}`
                  );
                "
              >
                <el-button
                  style="color: red"
                  :key="danger"
                  :type="danger"
                  link
                  @click="deleteUnit(chapterIndex, unitIndex)"
                  ><el-icon><Delete /></el-icon
                ></el-button>
                <span>{{ unit }}</span>
              </el-menu-item>
              <el-menu-item
                :index="'1-' + chapterIndex + '-' + 'input'"
                v-if="chapter.newVisible"
              >
                <el-input
                  placeholder="请输入章节名称"
                  v-model="newName"
                ></el-input>
                <el-button
                  style="color: blue"
                  :key="primary"
                  :type="primary"
                  link
                  @click="addUnit(chapterIndex)"
                  ><el-icon><Check /></el-icon
                ></el-button>
              </el-menu-item>
              <el-menu-item
                :index="'1-' + chapterIndex + '-' + 'new'"
                @click="chapter.newVisible = true"
              >
                <el-icon style="color: blue"> <CirclePlus /></el-icon>
                <span style="color: blue">添加节</span>
              </el-menu-item>
            </el-sub-menu>
            <el-menu-item :index="'2-' + 'input'" v-if="newChapterVisible">
              <el-input
                placeholder="请输入章节名称"
                v-model="newName"
              ></el-input>
              <el-button
                style="color: blue"
                :key="primary"
                :type="primary"
                link
                @click="addChapter()"
                ><el-icon><Check /></el-icon
              ></el-button>
            </el-menu-item>
            <el-menu-item index="2-new" @click="newChapterVisible = true">
              <el-icon style="color: blue"> <CirclePlus /></el-icon>
              <span style="color: blue">添加章</span>
            </el-menu-item>
          </el-sub-menu>
          <el-menu-item
            index="3"
            @click="
              this.$router.push(
                `/course-manager/homework/${this.$route.params.courseId}`
              )
            "
          >
            <span>作业管理</span>
          </el-menu-item>
        </el-menu>
      </el-scrollbar>
    </el-aside>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script>
import {
  CirclePlus,
  Delete,
  Check,
  Top,
  Bottom,
} from "@element-plus/icons-vue";
export default {
  name: "CourseManagerView",
  data() {
    return {
      newName: "",
      newChapterVisible: false,
      Selected: {
        chapter: NaN,
        unit: NaN,
      },
      chapters: [
        {
          name: "第一章",
          newVisible: false,
          units: ["第一节", "第二节", "第三节"],
        },
        {
          name: "第二章",
          newVisible: false,
          units: ["第一节", "第二节", "第三节"],
        },
      ],
    };
  },
  components: { CirclePlus, Delete, Check, Top, Bottom },
  methods: {
    onError(err, file, fileList) {
      this.dialogVisible = true;
    },
    deleteUnit(chapter, unit) {
      console.log(chapter, unit);
      console.log(this.chapters);
      this.chapters[chapter].units.splice(unit, 1);
      console.log(this.chapters);
    },
    addUnit(chapter) {
      this.chapters[chapter].units.push(this.newName);
      this.chapters[chapter].newVisible = false;
      this.newName = "";
    },
    addChapter() {
      this.chapters.push({
        name: this.newName,
        newVisible: false,
        units: [],
      });
      this.newChapterVisible = false;
      this.newName = "";
    },
    deleteChapter(chapter) {
      this.chapters.splice(chapter, 1);
    },
    upChapter(chapterIndex) {
      if (chapterIndex === 0) return;
      let chapter = this.chapters[chapterIndex];
      this.chapters.splice(chapterIndex, 1);
      this.chapters.splice(chapterIndex - 1, 0, chapter);
    },
    bottomChapter(chapterIndex) {
      if (chapterIndex === this.chapters.length - 1) return;
      let chapter = this.chapters[chapterIndex];
      this.chapters.splice(chapterIndex, 1);
      this.chapters.splice(chapterIndex + 1, 0, chapter);
    },
    upUnit(chapterIndex, unitIndex) {
      if (unitIndex === 0) return;
      let unit = this.chapters[chapterIndex].units[unitIndex];
      this.chapters[chapterIndex].units.splice(unitIndex, 1);
      this.chapters[chapterIndex].units.splice(unitIndex - 1, 0, unit);
    },
    bottomUnit(chapterIndex, unitIndex) {
      if (unitIndex === this.chapters[chapterIndex].units.length - 1) return;
      let unit = this.chapters[chapterIndex].units[unitIndex];
      this.chapters[chapterIndex].units.splice(unitIndex, 1);
      this.chapters[chapterIndex].units.splice(unitIndex + 1, 0, unit);
    },
    up() {
      if (isNaN(this.Selected.chapter)) return;
      if (isNaN(this.Selected.unit)) {
        this.upChapter(this.Selected.chapter);
      } else {
        this.upUnit(this.Selected.chapter, this.Selected.unit);
      }
    },
    bottom() {
      if (isNaN(this.Selected.chapter)) return;
      if (isNaN(this.Selected.unit)) {
        this.bottomChapter(this.Selected.chapter);
      } else {
        this.bottomUnit(this.Selected.chapter, this.Selected.unit);
      }
    },
    async changeSelected(chapter, unit) {
      if (!isNaN(unit)) {
        await new Promise((resolve) => {
          setTimeout(() => {
            resolve();
          }, 500);
        });
      }
      this.Selected = {
        chapter: chapter,
        unit: unit,
      };
    },
  },
};
</script>

<style>
.video-component-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 70%;
}
.up-and-down {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}
</style>
