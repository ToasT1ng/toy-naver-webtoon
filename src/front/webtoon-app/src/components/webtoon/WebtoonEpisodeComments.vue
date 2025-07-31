<script setup lang="ts">

// const { fetchComments, postComment } = useCommentApi()

import {ref} from "vue";

const comments = ref([])
const newComment = ref('')

// onMounted(async () => {
//   comments.value = await fetchComments()
// })

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
        <span class="caption grey--text me-2">{{ comments.length }}</span>
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