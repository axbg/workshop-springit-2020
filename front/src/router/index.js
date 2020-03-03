import Vue from 'vue';
import VueRouter from 'vue-router';
import VueMaterial from 'vue-material';
import Home from '../views/Home.vue';
import Login from '../views/Login.vue';
import NoteDashboard from '../components/NoteDashboard.vue';
import Note from '../components/Note.vue';

Vue.use(VueRouter);
Vue.use(VueMaterial);

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/',
    name: 'Home',
    component: Home,
    children: [
      {
        path: '',
        component: NoteDashboard,
      },
      {
        path: '/note/:id',
        component: Note,
      },
    ],
  },
];

const router = new VueRouter({
  routes,
});

export default router;
