<script setup lang="ts">
import {ref} from 'vue'
import {useLogin} from "@/composables/useLogin";
import router from "@/router";
import {useUserStore} from "@/stores/userStore";

const username = ref('')
const password = ref('')
const errorMessage = ref('')

const userStore = useUserStore()

const { mutateAsync, isPending } = useLogin()

const goHome = () => {
  router.push('/')
}

const handleLogin = async () => {
  if (!username.value || !password.value) {
    alert('아이디와 비밀번호를 입력하세요.')
    return
  }

  try {
    const result = await mutateAsync({
      username: username.value,
      password: password.value
    })
    userStore.login(result.userId, result.nickname, result.profileImage, result.accessToken, result.refreshToken)
    router.push('/')
  } catch (e) {
    errorMessage.value = (e as Error).message || '로그인에 실패했습니다.'
  }
}
</script>

<template>
  <div class="login-wrapper">
    <h1 class="logo-title" @click="goHome">WEBTOON</h1>

    <div class="login-container">
      <form @submit.prevent="handleLogin">

        <div class="form-group">
          <div class="input-wrapper">
            <input
                id="username"
                type="text"
                v-model="username"
                placeholder="아이디"
            />
          </div>
        </div>

        <div class="form-group">
          <div class="input-wrapper">
            <input
                id="password"
                type="password"
                v-model="password"
                placeholder="비밀번호"
            />
          </div>
        </div>

        <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>

        <button type="submit">로그인</button>
      </form>

      <div class="login-links">
        <a href="#">아이디 찾기</a>
        <a href="#">비밀번호 찾기</a>
        <a href="#">회원가입</a>
      </div>
    </div>
  </div>
</template>

<style scoped>
.login-wrapper {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f9f9f9;
}

.logo-title {
  font-size: 3rem;
  font-weight: 900;
  margin-bottom: 3rem;
  color: #333;
  cursor: pointer;
}

.login-container {
  width: 40%;
  padding: 2rem;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: #fff;
}

.form-group {
  margin-bottom: 1rem;
}

.input-wrapper {
  border: 1px solid #ddd;
  border-radius: 4px;
  padding: 0.25rem 0.5rem;
}

input {
  width: 100%;
  padding: 0.5rem;
  border: none;
  outline: none;
  background: transparent;
  box-sizing: border-box;
  font-size: 1rem;
  color: #000;
}

input::placeholder {
  color: #999;
}

button {
  width: 100%;
  padding: 0.75rem;
  background-color: #333;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #000;
}

.login-links {
  margin-top: 1.5rem;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 0.8rem;
}

.login-links a {
  color: #666;
  text-decoration: none;
  padding: 0 1.3rem;
  position: relative;
  display: inline-block;
  line-height: 0.8;
}

.login-links a + a {
  border-left: 1px solid #ddd;
}

.error-message {
  color: red;
  font-size: 0.9rem;
  margin-bottom: 1rem;
  text-align: center;
}
</style>