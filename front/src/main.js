import Vue from 'vue';
import VueMaterial from 'vue-material';
import 'vue-material/dist/vue-material.min.css';
import Toasted from 'vue-toasted';
import sanitizeHTML from 'sanitize-html';
import App from './App.vue';
import router from './router';

Vue.config.productionTip = false;
Vue.use(VueMaterial);
Vue.use(Toasted, { duration: 1500, singleton: true });
Vue.prototype.$sanitize = sanitizeHTML;

Vue.prototype.$baseUrl = 'http://localhost:8080';

new Vue({
  router,
  render: (h) => h(App),
}).$mount('#app');
