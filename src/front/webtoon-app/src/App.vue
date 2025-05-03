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

<template>
  <v-app>
    <TopBar/>
    <TabBar/>
    <router-view v-slot="{ Component }">
      <v-expand-transition>
        <component :is="Component" />
      </v-expand-transition>
    </router-view>
  </v-app>
</template>

