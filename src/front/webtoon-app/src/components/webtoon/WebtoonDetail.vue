<script setup lang="ts">
import {onMounted, watch} from "vue";
import WebtoonEpisodeList from "@/components/webtoon/WebtoonEpisodeList.vue";
import {useWebtoonStore} from "@/stores/webtoonStore";
import WebtoonDetailInformation from "@/components/webtoon/WebtoonDetailInformation.vue";
import {isWeekPageTab} from "@/types/common";

interface Props {
  webtoonId: string
  tab: string
}

const props = defineProps<Props>()

const webtoonStore = useWebtoonStore()

watch(
    () => [props.webtoonId, props.tab],
    ([newWebtoonId, newTab]) => {
      if (newWebtoonId) {
        webtoonStore.setWebtoonId(Number(newWebtoonId))
      }
      if (isWeekPageTab(newTab)) {
        webtoonStore.setTab(newTab)
      } else {
        console.warn(`Invalid tab: ${newTab}`)
      }
    },
    {immediate: true}
)

onMounted(() => {
  console.log('webtoonId:', props.webtoonId)
  console.log('tab:', props.tab)
})

</script>

<template>
  <WebtoonDetailInformation/>
  <v-card>
    <!-- 광고 배너  -->
  </v-card>
  <WebtoonEpisodeList/>
</template>