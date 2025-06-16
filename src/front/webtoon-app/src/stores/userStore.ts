import {defineStore} from "pinia";

interface UserState {
    userId: number | undefined
    nickname: string | undefined
    profileImage: string | undefined
    accessToken: string | undefined
    refreshToken: string | undefined
}

export const useUserStore = defineStore('user', {
    state: (): UserState => ({
        userId: undefined,
        nickname: undefined,
        profileImage: undefined,
        accessToken: undefined,
        refreshToken: undefined,
    }),
    actions: {
        login(userId: number, nickname: string | undefined, profileImage: string | undefined, accessToken: string, refreshToken: string) {
            this.userId = userId
            this.nickname = nickname
            this.profileImage = profileImage
            this.accessToken = accessToken
            this.refreshToken = refreshToken
        },
        logout() {
            this.userId = undefined
            this.nickname = undefined
            this.profileImage = undefined
            this.accessToken = undefined
            this.refreshToken = undefined
        },
    },
})