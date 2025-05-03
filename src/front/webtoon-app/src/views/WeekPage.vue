<script lang="ts">
import WeekBar from '@/components/WeekBar.vue';
import MonthlyWebtoon from '@/components/MonthlyWebtoon.vue';

export default {
  name: 'WeekPage',
  components: {
    WeekBar,
    MonthlyWebtoon,
  },
};
</script>

<template>
  <div>
    <v-expand-transition>
      <WeekBar />
    </v-expand-transition>
    <v-main>
      <MonthlyWebtoon />
    </v-main>
  </div>
</template>
