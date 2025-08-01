<script setup lang="ts">
import {onMounted, ref} from "vue";
import {navigateToWebtoonDetail, navigateToWebtoonEpisode} from "@/utils/navigation";
import {useWebtoonStore} from "@/stores/webtoonStore";
import {useWebtoonEpisode, useWebtoonEpisodeNavigation} from "@/composables/useWebtoonEpisodes";
import WebtoonEpisodeComments from "@/components/webtoon/WebtoonEpisodeComments.vue";

interface Props {
  webtoonId: string;
  episodeId: string;
}

const props = defineProps<Props>();
const webtoonStore = useWebtoonStore();

const {
  data: webtoonEpisodeNavigationData,
  updateWebtoonId: webtoonEpisodeNavigationUpdateWebtoonId,
  updateEpisodeId: webtoonEpisodeNavigationUpdateEpisodeId
} = useWebtoonEpisodeNavigation()

const {
  data: webtoonEpisodeData,
  updateWebtoonId: webtoonEpisodeUpdateWebtoonId,
  updateEpisodeId: webtoonEpisodeUpdateEpisodeId
} = useWebtoonEpisode()

onMounted(() => {
  console.log("episodeId:", props.episodeId);
  console.log("tab:", webtoonStore.tab);
  webtoonEpisodeUpdateWebtoonId(Number(props.webtoonId));
  webtoonEpisodeUpdateEpisodeId(Number(props.episodeId));
  webtoonEpisodeNavigationUpdateWebtoonId(Number(props.webtoonId));
  webtoonEpisodeNavigationUpdateEpisodeId(Number(props.episodeId));
});

function onClickNavigateToEpisode(episodeId: number) {
  if (episodeId !== 0) {
    navigateToWebtoonEpisode(Number(props.webtoonId), episodeId);
  } else {
    console.log("없습니다.");
  }
}
</script>

<template>
  <v-card class="pa-0 rounded-0" elevation="0">
    <v-row class="episode-header no-gutters justify-space-between">
      <v-col class="title-area d-flex align-center ml-10" style="height: 50px; overflow: visible;">
        <span class="title-text mr-4"><a>〈</a></span>
        <span class="title-text with-divider">{{ webtoonEpisodeData?.webtoonTitle }}</span>
        <span class="title-text">{{ webtoonEpisodeData?.title }}</span>
      </v-col>
      <v-col class="tool-area d-flex justify-end align-center mr-10" style="height: 50px;" cols="auto">
        <div class="button-group">
          <v-btn class="nav-button" @click="onClickNavigateToEpisode(webtoonEpisodeNavigationData?.previousEpisodeId)">
            <v-icon>mdi-menu-left</v-icon>
            이전화
          </v-btn>
          <v-btn class="nav-button" @click="navigateToWebtoonDetail(Number(props.webtoonId), webtoonStore.tab)">
            <v-icon>mdi-format-list-bulleted</v-icon>
            목록
          </v-btn>
          <v-btn class="nav-button" @click="onClickNavigateToEpisode(webtoonEpisodeNavigationData?.nextEpisodeId);">다음화
            <v-icon>mdi-menu-right</v-icon>
          </v-btn>
        </div>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12">
        <div v-if="webtoonEpisodeData?.images?.length">
          <div
            v-for="(img, index) in webtoonEpisodeData.images"
            :key="index"
            class="image-wrapper"
          >
            <v-img
              :src="img"
              class="mb-4"
              height="100%"
              cover
            />
          </div>
        </div>
        <div v-else>
          <p>이미지가 없습니다.</p>
        </div>
      </v-col>
    </v-row>
    <v-row class="justify-center mt-10 pt-10">
      <v-col cols="6">
        <v-card elevation="0">
          <v-card-text class="pa-4">
            <v-row justify="space-around" align="center" class="box-border">
              <v-col class="d-flex flex-column align-center ma-2">
                <v-icon size="32">mdi-bookmark-outline</v-icon>
                <span class="subtitle-1 mt-2">관심웹툰</span>
                <span class="headline font-weight-bold">42</span>
              </v-col>
              <v-col class="d-flex flex-column align-center ma-2">
                <v-icon size="32">mdi-thumb-up-outline</v-icon>
                <span class="subtitle-1 mt-2">좋아요</span>
                <span class="headline font-weight-bold">128</span>
              </v-col>
              <v-col class="d-flex flex-column align-center ma-2">
                <v-icon size="32">mdi-star-outline</v-icon>
                <span class="subtitle-1 mt-2">별점주기</span>
                <span class="headline font-weight-bold">5.0</span>
              </v-col>
              <v-col class="d-flex flex-column align-center ma-2">
                <v-icon size="32">mdi-share-variant</v-icon>
                <span class="subtitle-1 mt-2">공유하기</span>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>

    <v-divider class="mt-10"/>
    <v-row class="justify-center">
      <v-col cols="11">
        <v-card-text>
          에피소드 나열
        </v-card-text>
      </v-col>
    </v-row>
    <v-row class="justify-center">
      <v-col cols="11">
        <v-divider class="mt-2"/>
        <WebtoonEpisodeComments :episode-id="props.episodeId"/>
      </v-col>
    </v-row>
  </v-card>
</template>

<style scoped>
.episode-header {
  background-color: black;
  height: 56px;
  box-sizing: border-box;
  margin-top: 1px;
}

.title-area,
.tool-area {
  height: 100%;
  display: flex;
  align-items: center;
}

.title-text {
  color: white;
  font-weight: bold;
  font-size: 16px;
  line-height: normal;
  position: relative;
}

.title-text.with-divider {
  margin-right: 20px;
}

::v-deep(.with-divider::after) {
  content: "";
  position: absolute;
  right: -10px;
  top: 53%;
  transform: translateY(-50%);
  height: 13px;
  width: 1px;
  background-color: #e0e0e0;
  opacity: 0.3;
}

.button-group {
  display: flex;
  gap: 20px;
  position: relative;
}

.nav-button {
  color: white;
  font-weight: bold;
  background-color: black;
  font-size: 16px;
  height: 40px;
  line-height: 40px;
  padding: 0 8px;
  position: relative;
}

::v-deep(.nav-button + .nav-button::before) {
  content: "";
  position: absolute;
  left: -10px;
  top: 53%;
  transform: translateY(-50%);
  height: 12px;
  width: 1px;
  background-color: #e0e0e0;
  opacity: 0.3;
}
</style>
<style scoped>
.image-wrapper {
  margin-left: 25%;
  margin-right: 25%;
}

.image-wrapper .v-img {
  width: 100%;
  max-width: 100%;
  object-fit: contain;
}

.box-border {
  border: 1px solid #ccc;
}
</style>