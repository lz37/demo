import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import LoginView from "./views/Login.vue";
import SignupView from "./views/Signup.vue";
import StudentMenu from "./views/StudentMenu.vue";
import CourseComponent from "./components/student/course.vue";

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
    path: "/student-menu",
    name: "student-menu",
    component: StudentMenu,
    children: [
      {
        path: "",
        name: "CourseComponent",
        component: CourseComponent,
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
