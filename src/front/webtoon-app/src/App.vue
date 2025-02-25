<template>
  <v-app>
    <TopBar/>
    <TabBar v-model:activeTab="activeTab" />

    <v-expand-transition>
      <WeekBar v-if="activeTab === 'week'" />
      <MyPage v-if="activeTab === 'mypage'" />
    </v-expand-transition>
    <v-main v-if="activeTab === 'week'">
      <MonthlyWebtoon/>
    </v-main>
  </v-app>
</template>

<script>
import TopBar from '@/components/TopBar.vue'
import TabBar from '@/components/TabBar.vue'
import WeekBar from '@/components/WeekBar.vue'
import MyPage from '@/components/MyPage.vue'
import MonthlyWebtoon from "@/components/MonthlyWebtoon.vue";

export default {
  name: 'App',
  components: {
    TopBar,
    TabBar,
    WeekBar,
    MyPage,
    MonthlyWebtoon,
  },
  data() {
    return {
      activeTab: 'week'
    };
  }
}
</script>