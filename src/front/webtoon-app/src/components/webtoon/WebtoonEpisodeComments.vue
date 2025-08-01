<script setup lang="ts">
import {useWebtoonComments} from "@/composables/useWebtoonComments";
import {computed, ref, watch} from "vue";
import {useWebtoonStore} from "@/stores/webtoonStore";

interface Props {
  episodeId: string;
}
const props = defineProps<Props>();

const webtoonStore = useWebtoonStore()

const {
  data: commentsData,
  updateWebtoonId: webtoonEpisodeCommentsUpdateWebtoonId,
  updateEpisodeId: webtoonEpisodeCommentsUpdateEpisodeId,
} = useWebtoonComments()

const newComment = ref('')
const commentsList = computed(() => commentsData.value?.content ?? []);

watch(
    () => webtoonStore.webtoonId,
    (newWebtoonId) => {
      if (newWebtoonId) {
        console.log("input 1:", newWebtoonId)
        webtoonEpisodeCommentsUpdateWebtoonId(Number(newWebtoonId))
      }
    },
    {immediate: true}
)

watch(
    () => props.episodeId,
    (newEpisodeId) => {
      if (newEpisodeId) {
        console.log("input 1:", newEpisodeId)
        webtoonEpisodeCommentsUpdateEpisodeId(Number(newEpisodeId))
      }
    },
    {immediate: true}
)

async function refreshComments() {
  // comments.value =
}

async function submitComment() {
  if (!newComment.value.trim()) return
  // const created = await postComment(newComment.value)
  // comments.value.push(created)
  newComment.value = ''
}
</script>
<template>
  <v-card elevation="0" class="mt-2">
    <v-card-title class="d-flex align-center">
      <div class="d-flex align-center">
        <span class="font-weight-bold me-2">댓글</span>
        <span class="caption grey--text me-2">{{ commentsList.length }}</span> <!-- TODO 정확한 갯수 -->
        <v-btn
            icon
            x-small
            class="refresh-btn"
            @click="refreshComments"
            elevation="0"
        >
          <v-icon size="16">mdi-refresh</v-icon>
        </v-btn>
      </div>
    </v-card-title>
    <v-card-text>
      <div class="textarea-wrapper">
        <v-textarea
            solo
            name="input-7-4"
            label="댓글을 입력하세요"
            base-color="white"
            bg-color="white"
            class="box-border"
        ></v-textarea>
        <v-btn
            icon
            small
            class="submit-btn grey--bg lighten-2"
            :disabled="!newComment.trim()"
            @click="submitComment"
            elevation="0"
        >
          <v-icon size="22" color="white">mdi-send</v-icon>
        </v-btn>
      </div>
    </v-card-text>
    <v-card-text>
      <v-list dense>
        <v-list-item v-for="comment in commentsList" :key="comment.id" class="py-2">
          <v-list-item-avatar>
            <v-avatar size="32">
              <v-img v-if="comment.user.profileImageUrl" :src="comment.user.profileImageUrl" />
              <v-icon v-else size="32">mdi-account-circle</v-icon>
            </v-avatar>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title>{{ comment.user.username }}</v-list-item-title>
            <v-list-item-subtitle class="mb-1">{{ comment.content }}</v-list-item-subtitle>
            <v-list-item-subtitle class="grey--text caption">
              {{ new Date(comment.createdAt).toLocaleString() }}
            </v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        <v-list-item v-if="!commentsList.length">
          <v-list-item-content class="text-center grey--text caption">
            등록된 댓글이 없습니다.
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-card-text>
  </v-card>
</template>

<style scoped>
.textarea-wrapper {
  position: relative;
  width: 100%;
}

.submit-btn {
  position: absolute;
  bottom: 7px;
  right: 7px;
  min-width: auto !important;
  padding: 4px !important;
  width: 35px !important;
  height: 35px !important;
}

.refresh-btn {
  padding: 2px !important;
  min-width: auto !important;
  width: 24px !important;
  height: 24px !important;
  border: 1px solid #ccc;
}

.box-border {
  border: 1px solid #ccc;
}
</style>