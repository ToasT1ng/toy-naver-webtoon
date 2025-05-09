import router from "@/router";

export const navigateToWebtoonDetail = (id: number, tab: string) => {
    router.push({
        path: `/webtoon/detail/${id}`,
        query: {tab}
    }).then(r => {
        console.log(r)
    })
 }