<script lang="ts">
export default {
  name: 'TabBar',
};
</script>

<template>
  <v-app-bar app color="secondary" dark dense>
    <v-btn text :to="{ name: 'week' }">요일별 웹툰</v-btn>
    <v-btn text :to="{ name: 'mypage' }">마이페이지</v-btn>
  </v-app-bar>
</template>

