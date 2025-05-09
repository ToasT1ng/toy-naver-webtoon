import {createRouter, createWebHistory} from 'vue-router'
import WeekPage from '@/views/WeekPage.vue'
import MyPage from '@/views/MyPage.vue'
import MonthlyWebtoon from "@/components/webtoon/MonthlyWebtoon.vue";
import DailyWebtoon from "@/components/webtoon/DailyWebtoon.vue";
import WebtoonDetail from "@/components/webtoon/WebtoonDetail.vue";

const routes = [
    {
        path: '/webtoon',
        // redirect: '/webtoon/all',
        name: 'week',
        component: WeekPage,
        children: [
            // {
            //     path: '',
            //     redirect: 'all',
            // },
            {
                path: 'all',
                name: 'WebtoonAll',
                component: MonthlyWebtoon,
            },
            {
                path: ':daysOfWeekValue',
                name: 'WebtoonDay',
                component: DailyWebtoon,
                props: true,
            },
            {
                path: 'detail/:webtoonId',
                name: 'WebtoonDetail',
                component: WebtoonDetail,
                props: (route: { params: { webtoonId: any; }; query: { tab: any; }; }) => ({
                    webtoonId: route.params.webtoonId,
                    tab: route.query.tab
                }),
            },
        ]
    },
    {
        path: '/mypage',
        name: 'mypage',
        component: MyPage,
    },
    {
        path: '/',
        redirect: '/webtoon',
    },
]

const router = createRouter({
    history: createWebHistory(), // 또는 createWebHistory(process.env.BASE_URL) 사용 가능
    routes,
})

export default router
