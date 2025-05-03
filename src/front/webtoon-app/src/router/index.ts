import {createRouter, createWebHistory} from 'vue-router'
import WeekPage from '@/views/WeekPage.vue'
import MyPage from '@/views/MyPage.vue'

const routes = [
    {
        path: '/week',
        name: 'week',
        component: WeekPage,
    },
    {
        path: '/mypage',
        name: 'mypage',
        component: MyPage,
    },
    {
        path: '/',
        redirect: '/week',
    },
]

const router = createRouter({
    history: createWebHistory(), // 또는 createWebHistory(process.env.BASE_URL) 사용 가능
    routes,
})

export default router
