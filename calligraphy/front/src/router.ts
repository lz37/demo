import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
import LoginView from "./views/Login.vue";
import SignupView from "./views/Signup.vue";
import MenuView from "./views/Menu.vue";
import CourseComponent from "./components/student/Course.vue";
import CopybookComponent from "./components/student/Copybook.vue";
import CollectionComponent from "./components/student/Collection.vue";
import MisprintComponent from "./components/student/Misprint.vue";
import HomeworkComponent from "./components/student/Homework.vue";
import CourseView from "./views/Course.vue";
import CopybookView from "./views/Copybook.vue";
import CourseManagerComponent from "./components/teacher/CourseManager.vue";
import CourseManagerView from "./views/CourseManager.vue";
import CourseManagerInnerComponent from "./components/teacher/CourseManagerInner.vue";
import CourseManagerHomework from "./components/teacher/CourseManagerHomework.vue";
import AnnouncementComponent from "./components/admin/Announcement.vue";
import SchoolComponent from "./components/admin/School.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "login",
    component: LoginView,
  },
  {
    path: "/signup",
    name: "signup",
    component: SignupView,
  },
  {
    path: "/menu",
    name: "menu",
    component: MenuView,
    children: [
      {
        path: "",
        name: "CourseComponent",
        component: CourseComponent,
      },
      {
        path: "copybook",
        name: "CopybookComponent",
        component: CopybookComponent,
      },
      {
        path: "collection",
        name: "CollectionComponent",
        component: CollectionComponent,
      },
      {
        path: "misprint",
        name: "MisprintComponent",
        component: MisprintComponent,
      },
      {
        path: "homework",
        name: "Homework",
        component: HomeworkComponent,
      },
      {
        path: "course-manager",
        name: "CourseManagerComponent",
        component: CourseManagerComponent,
      },
      {
        path: "announcement",
        name: "AnnouncementComponent",
        component: AnnouncementComponent,
      },
      {
        path: "school",
        name: "SchoolComponent",
        component: SchoolComponent,
      },
    ],
  },
  {
    path: "/course/:courseId",
    name: "course",
    component: CourseView,
  },
  {
    path: "/course-manager",
    name: "courseManager",
    component: CourseManagerView,
    children: [
      {
        path: ":courseId",
        name: "CourseManagerInnerComponent",
        component: CourseManagerInnerComponent,
      },
      {
        path: "homework/:courseId",
        name: "CourseManagerHomework",
        component: CourseManagerHomework,
      },
    ],
  },
  {
    path: "/copybook",
    name: "copybook",
    component: CopybookView,
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
