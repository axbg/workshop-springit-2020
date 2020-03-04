<template>
  <div class="container">
    <div class="login-container">
      <md-card class="md-elevation-12">
        <md-card-content class="credentials-container">
          <md-field md-clearable>
            <label>Email</label>
            <md-input v-model="email" ref="email"></md-input>
          </md-field>
          <md-field :md-toggle-password="true">
            <label>Password</label>
            <md-input v-model="password" type="password" ref="password"></md-input>
          </md-field>
          <md-button class="md-raised md-accent button" @click="login">Login</md-button>
          <md-button class="md-raised md-accent button" @click="register">Register</md-button>
        </md-card-content>
      </md-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'login',
  props: ['baseUrl'],
  data: () => ({
    email: '',
    password: '',
  }),
  methods: {
    async register() {
      if (this.validateCredentials()) {
        try {
          const response = await this.$fetchHandler(
            `${this.$baseUrl}/user/register`,
            'POST',
            {
              email: this.email,
              password: this.password,
            },
            'omit',
          );

          if (response.status === 200) {
            this.$toasted.show('Congrats! You can login now!');
          } else {
            this.$toasted.show('Credentials are either invalid or already existing');
          }
        } catch (ex) {
          this.$toasted.show('Invalid credentials');
        }
      }
    },
    async login() {
      if (this.validateCredentials()) {
        try {
          const response = await this.$fetchHandler(
            `${this.$baseUrl}/user/login`,
            'POST',
            {
              email: this.email,
              password: this.password,
            },
          );
          if (response.status === 200) {
            localStorage.setItem('loggedIn', true);
            window.location.reload();
          } else {
            this.$toasted.show('Credentials are invalid');
          }
        } catch (ex) {
          this.$toasted.show('Incorrect credentials');
        }
      }
    },
    validateCredentials() {
      if (
        this.email === ''
        || !this.email.includes('@')
        || !this.email.includes('.')
      ) {
        this.email = '';
        this.$refs.email.$el.focus();
        this.$toasted.show('Email is not valid');
        return false;
      }
      if (this.password === '') {
        this.password = '';
        this.$refs.password.$el.focus();
        this.$toasted.show('Password cannot be empty');
        return false;
      }

      return true;
    },
  },
};
</script>

<style scoped>
@import "../assets/variables.css";

.container {
  height: 100%;
  background-color: var(--main-color);
}
.login-container {
  margin: 0 auto;
  width: 30%;
  margin-top: 20vh;
  display: inline-block;
  background-color: var(--background-color);
}
.credentials-container {
  margin: 0 auto;
  width: 80%;
}
.button {
  background-color: var(--accent-color);
  color: white;
}
@media only screen and (max-width: 1200px) {
  .login-container {
    width: 90%;
  }
}
</style>
