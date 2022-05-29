import {
  createRouter,
  createWebHistory,
  createWebHashHistory,
  RouteRecordRaw,
} from "vue-router";
import MainView from "./views/MainView.vue";
import LoginView from "./views/LoginView.vue";
import RegisterView from "./views/RegisterView.vue";
import BusinessView from "./views/BusinessView.vue";
import PanelView from "./views/PanelView.vue";
import SumView from "./views/Admin/SumView.vue";
import Ceiling from "./views/Admin/CeilingView.vue";
import RankView from "./views/Admin/RankView.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "MainView",
    component: MainView,
  },
  {
    path: "/login",
    name: "LoginView",
    component: LoginView,
  },
  {
    path: "/register",
    name: "RegisterView",
    component: RegisterView,
  },
  {
    path: "/business",
    name: "PanelView",
    component: PanelView,
    children: [
      {
        path: "",
        name: "BusinessView",
        component: BusinessView,
      },
      {
        path: "sum",
        name: "SumView",
        component: SumView,
      },
      {
        path: "ceiling",
        name: "Ceiling",
        component: Ceiling,
      },
      {
        path: "rank",
        name: "RankView",
        component: RankView,
      },
    ],
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
