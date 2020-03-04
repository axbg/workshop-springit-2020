import Vue from 'vue';
import VueMaterial from 'vue-material';
import 'vue-material/dist/vue-material.min.css';
import InfiniteLoading from 'vue-infinite-loading';
import Toasted from 'vue-toasted';
import App from './App.vue';
import router from './router';

Vue.config.productionTip = false;
Vue.use(VueMaterial);
Vue.use(Toasted, { duration: 1500, singleton: true });
Vue.use(InfiniteLoading, {
  slots: {
    noResults: 'That\'s all for now boy',
    noMore: 'Nothing more',
  },
});

Vue.prototype.$baseUrl = 'http://localhost:8080';

new Vue({
  router,
  render: (h) => h(App),
}).$mount('#app');
