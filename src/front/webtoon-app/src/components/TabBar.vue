<template>
  <v-app-bar app color="secondary" dark dense>
    <v-btn text @click="changeTab('week')">요일별 웹툰</v-btn>
    <v-btn text @click="changeTab('mypage')">마이페이지</v-btn>
  </v-app-bar>
</template>

<script>
export default {
  name: 'SecondBar',
  props: {
    activeTab: {
      type: String,
      default: 'week'
    }
  },
  methods: {
    changeTab(tab) {
      this.$emit('update:activeTab', tab);
    }
  }
}
</script>
