import Vue from 'vue';
import VueRouter from 'vue-router';
import VueMaterial from 'vue-material';
import Home from '../views/Home.vue';
import Login from '../views/Login.vue';
import NoteDashboard from '../components/NoteDashboard.vue';
import Note from '../components/Note.vue';
import PublicNote from '../views/PublicNote.vue';

Vue.use(VueRouter);
Vue.use(VueMaterial);

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/public/:id',
    name: 'PublicNote',
    component: PublicNote,
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

router.beforeEach((to, from, next) => {
  if (localStorage.getItem('loggedIn')) {
    if (to.name === 'Login') {
      next('/');
    } else {
      next();
    }
  } else if (to.name === 'Login' || to.name === 'PublicNote') {
    next();
  } else {
    next('/login');
  }
});

export default router;
