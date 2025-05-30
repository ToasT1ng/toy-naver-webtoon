import apiClient from './index'

interface IDailyRecommendedWebtoonsResponse {
    result: IDailyRecommendedWebtoonResponse[]
}

interface IDailyRecommendedWebtoonResponse {
    id: number
    title: string
    thumbnail: string
    authorNames: string,
    latestEpisodeName: string,
    rating: string,
}

export const getDailyRecommendedWebtoon = async (
    req: IDailyWebtoonsRequest
): Promise<IDailyRecommendedWebtoonsResponse> => {
    const response = await apiClient.get<IDailyRecommendedWebtoonsResponse>('/webtoons/daily/recommend/three', {
        params: req,
    })
    return response.data
}

interface IDailyWebtoonsRequest {
    dayOfWeek: string
}

interface IDailyWebtoonsResponse {
    result: IDailyWebtoonResponse[]
}

interface IDailyWebtoonResponse {
    id: number
    title: string
    thumbnail: string
    authorNames: string,
    rating: string,
}

export const getDailyWebtoon = async (
    req: IDailyWebtoonsRequest
): Promise<IDailyWebtoonsResponse> => {
    const response = await apiClient.get<IDailyWebtoonsResponse>('/webtoons/daily', {
        params: req,
    })
    return response.data
}

interface IMonthlyRecommendedWebtoonsResponse {
    result: IMonthlyRecommendedWebtoonResponse[]
}

interface IMonthlyRecommendedWebtoonResponse {
    id: number
    title: string
    thumbnail: string
    authorNames: string,
    description: string,
}

export const getMonthlyRecommendedWebtoon = async (): Promise<IMonthlyRecommendedWebtoonsResponse> => {
    const response = await apiClient.get<IMonthlyRecommendedWebtoonsResponse>('/webtoons/monthly/recommend/three',)
    return response.data
}

interface IEveryWebtoonResponse {
    result: Record<string, IEachWebtoonResponse[]>;
}

interface IEachWebtoonResponse {
    id: number
    title: string
    thumbnail: string
}

export const getEveryWebtoon = async (): Promise<IEveryWebtoonResponse> => {
    const response = await apiClient.get<IEveryWebtoonResponse>('/webtoons',)
    return response.data
}

interface IGetWebtoonRequest {
    id: number
}

interface IPerson {
    personId: number;
    name: string;
}

interface IGetWebtoonResponse {
    id: number;
    title: string;
    writer: IPerson
    illustrator: IPerson
    originalAuthor?: IPerson
    dayOfWeek: string;
    restrictedAge: number;
    description: string;
    tags: string[];
    thumbnail: string;
    likeCount: number;
}

export const getWebtoon = async (req: IGetWebtoonRequest): Promise<IGetWebtoonResponse> => {
    const response = await apiClient.get<IGetWebtoonResponse>(`/webtoons/${req.id}`,)
    return response.data
}