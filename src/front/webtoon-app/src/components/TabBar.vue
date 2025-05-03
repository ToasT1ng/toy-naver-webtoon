<script lang="ts">
import { useRoute } from 'vue-router';

export default {
  name: 'TabBar',
  setup() {
    const route = useRoute();
    return { route };
  },
};
</script>

<template>
  <v-app-bar app color="white" light elevation="0" class="tab-bar">
    <v-btn
           :to="{ name: 'week' }"
           :class="['text-subtitle-1', { active: route.name === 'week' }]"
    >
      요일별 웹툰
    </v-btn>
    <v-btn
           :to="{ name: 'mypage' }"
           :class="['text-subtitle-1', { active: route.name === 'mypage' }]"
    >
      마이페이지
    </v-btn>
  </v-app-bar>
</template>

<style scoped>
.tab-bar {
  border: 1px solid #ccc;
}
.v-btn {
  border-radius: 0;
  height: 100%;
  font-weight: 700;
}
.v-btn.active {
  background-color: #f0f0f0;
}
</style>