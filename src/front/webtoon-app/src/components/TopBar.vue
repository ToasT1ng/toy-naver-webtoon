<script setup lang="ts">
import {useRouter} from 'vue-router'
import {useUserStore} from '@/stores/userStore'

const userStore = useUserStore()
const router = useRouter()

const logout = () => {
  userStore.logout()
  //TODO: 로그아웃 API 호출 필요
  router.push('/login')
}
</script>

<template>
  <v-app-bar app color="white" light elevation="0">
    <v-toolbar-title>내이불 웹툰</v-toolbar-title>
    <v-spacer></v-spacer>

    <template v-if="!userStore.userId">
      <v-btn to="/login">로그인</v-btn>
      <v-btn to="/register">회원가입</v-btn>
    </template>

    <template v-else>
      <!-- 메뉴 버튼 -->
      <v-menu offset-y>
        <template #activator="{ props }">
          <v-btn icon v-bind="props">
            <v-avatar size="32">
              <img src="/default-profile.png" alt="프로필" />
            </v-avatar>
          </v-btn>
        </template>

        <!-- 드롭다운 콘텐츠 -->
        <v-card class="pa-4" width="250">
          <div class="profile-box">
            <v-avatar size="56">
              <img src="/default-profile.png" alt="프로필" />
            </v-avatar>
            <div class="profile-info">
              <div class="nickname">{{ userStore.nickname ?? '디폴트닉네임' }}</div>
            </div>
          </div>
          <v-divider class="my-3" />
          <v-btn block color="grey darken-1" @click="logout">로그아웃</v-btn>
        </v-card>
      </v-menu>
    </template>
  </v-app-bar>
</template>

<style scoped>
.v-toolbar-title {
  font-weight: 800;
}

.v-btn {
  color: rgba(94, 92, 92, 0.6);
}

.profile-box {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.profile-info .nickname {
  font-weight: 600;
  font-size: 1rem;
  color: #333;
}
</style>