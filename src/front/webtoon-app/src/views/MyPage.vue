<template>
  <div>
    <v-main>
      <MyPageComponent />
    </v-main>
  </div>
</template>

<script>
import MyPageComponent from '@/components/MyPage.vue';

export default {
  name: 'MyPage',
  components: {
    MyPageComponent,
  },
};
</script>
