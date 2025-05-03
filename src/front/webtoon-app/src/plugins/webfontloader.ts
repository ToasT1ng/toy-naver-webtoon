export async function loadFonts() {
    const webFontLoader = await import('webfontloader')

    webFontLoader.load({
        google: {
            families: ['Noto Sans KR:300,400,500,600,700,800,900&display=swap']
        },
        // active: () => {
        //   console.log('✅ Web font loaded')
        // },
        // inactive: () => {
        //   console.error('❌ Web font failed to load')
        // },
    })
}