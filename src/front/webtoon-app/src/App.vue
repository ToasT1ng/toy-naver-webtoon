<template>
  <v-app>
    <TopBar />
    <TabBar />
    <v-expand-transition>
      <router-view />
    </v-expand-transition>
  </v-app>
</template>

<script lang="ts">
import TopBar from '@/components/TopBar.vue';
import TabBar from '@/components/TabBar.vue';

export default {
  name: 'App',
  components: {
    TopBar,
    TabBar,
  },
};
</script>
