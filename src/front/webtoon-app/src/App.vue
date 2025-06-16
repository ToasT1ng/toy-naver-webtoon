<script setup lang="ts">
import {ref} from 'vue'
import TopBar from '@/components/TopBar.vue'
import TabBar from '@/components/TabBar.vue'
import router from '@/router'

const layoutVisible = ref(true)
router.beforeEach((to, from, next) => {
  layoutVisible.value = to.meta.layout !== 'none'
  next()
})

</script>

<template>
  <v-app>
    <TopBar v-if="layoutVisible"/>
    <TabBar v-if="layoutVisible"/>

    <router-view v-slot="{ Component }">
      <v-expand-transition>
        <component :is="Component"/>
      </v-expand-transition>
    </router-view>
  </v-app>
</template>

