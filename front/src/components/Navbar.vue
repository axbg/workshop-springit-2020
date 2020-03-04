<template>
  <nav>
    <md-button class="md-dense nav-button" @click="displayDashboard" v-if="displayControls">
      Home
    </md-button>
    <span class="banner">Accenture@SpringIT</span>
    <md-button class="md-dense nav-button" @click="logout" v-if="displayControls">Logout</md-button>
  </nav>
</template>

<script>
export default {
  name: 'Navbar',
  props: {
    displayControls: Boolean,
  },
  methods: {
    displayDashboard() {
      if (this.$route.path !== '/') {
        this.$router.push('/');
      }
    },
    logout() {
      localStorage.removeItem('loggedIn');
      window.location.reload();
    },
  },
};
</script>

<style scoped>
@import "../assets/variables.css";

nav {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  height: 60px;
  background-color: var(--main-color);
  border-bottom: 1px solid grey;
}
.nav-button {
  color: whitesmoke;
  background-color: var(--accent-color);
}
.home {
  margin-left: 5px;
}
.banner {
  color: var(--accent-color);
  font-size: 26px;
}
.logout {
  margin-right: 5px;
}
@media only screen and (max-width: 470px) {
  .banner {
    display: none;
  }
}
</style>
