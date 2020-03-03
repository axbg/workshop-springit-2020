<template>
  <nav>
    <md-button class="md-dense nav-button" @click="displayDashboard">Home</md-button>
    <span class="user">Accenture@SpringIT</span>
    <md-button class="md-dense nav-button" @click="logout">Logout</md-button>
  </nav>
</template>

<script>
export default {
  name: 'Navbar',
  methods: {
    displayDashboard() {
      if (this.$route.path !== '/') {
        this.$router.push('/');
      }
    },
    logout() {
      // remove isLoggedIn from localStorage
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
}
.nav-button {
  color: whitesmoke;
  background-color: var(--accent-color);
}
.home {
  margin-left: 5px;
}
.logout {
  margin-right: 5px;
}
@media only screen and (max-width: 385px) {
  .user {
    display: none;
  }
}
</style>
