package com.example.newsapp.util

class Constants {
    companion object {
        const val API_KEY = "750a0e96c7f8405fa57681a5000883fe"
        const val BASE_URL = "https://newsapi.org"
        const val PAGE_SIZE = 15
        const val TIMEOUTS = 60L
    }
}


//fun fakeData(): NewsRequest {
//    val data = " {\n" +
//            "        \"status\": \"ok\",\n" +
//            "        \"totalResults\": 132743,\n" +
//            "        \"articles\": [\n" +
//            "        {\n" +
//            "            \"source\": {\n" +
//            "            \"id\": \"engadget\",\n" +
//            "            \"name\": \"Engadget\"\n" +
//            "        },\n" +
//            "            \"author\": \"Jon Fingas\",\n" +
//            "            \"title\": \"T-Mobile pins latest data breach on SIM swapping\",\n" +
//            "            \"description\": \"T-Mobile is still suffering from data breaches, although its latest headache may be more reflective of the phone business at large. The carrier has confirmed to Bleeping Computer that a recent data breach stemmed from SIM swapping attacks. Intruders compromis…\",\n" +
//            "            \"url\": \"https://www.engadget.com/t-mobile-sim-swapping-data-breach-190612616.html\",\n" +
//            "            \"urlToImage\": \"https://s.yimg.com/os/creatr-uploaded-images/2021-12/49e16860-68d3-11ec-9eff-49365b33f997\",\n" +
//            "            \"publishedAt\": \"2021-12-29T19:06:12Z\",\n" +
//            "            \"content\": \"T-Mobile is still suffering from data breaches, although its latest headache may be more reflective of the phone business at large. The carrier has confirmed to Bleeping Computer that a recent data b… [+753 chars]\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"source\": {\n" +
//            "            \"id\": null,\n" +
//            "            \"name\": \"Zacks.com\"\n" +
//            "        },\n" +
//            "            \"author\": \"Zacks Equity Research\",\n" +
//            "            \"title\": \"Why Seasoned Investors are Retaining RenaissanceRe (RNR)\",\n" +
//            "            \"description\": \"RenaissanceRe Holdings (RNR) is focused on making strategic acquisitions to boost its business through inorganic growth.\",\n" +
//            "            \"url\": \"http://www.zacks.com/stock/news/1848010/why-seasoned-investors-are-retaining-renaissancere-rnr?cid=CS-ENTREPRENEUR-FT-analyst_blog|rank_focused-1848010\",\n" +
//            "            \"urlToImage\": \"https://assets.entrepreneur.com/providers/zacks/hero-image-zacks-411723.jpeg\",\n" +
//            "            \"publishedAt\": \"2022-01-05T20:08:00Z\",\n" +
//            "            \"content\": \"RenaissanceRe Holdings Ltd. RNR is well poised to grow on the back of strategic acquisitions and premium growth at both Casualty and Specialty plus Property businesses. Its diversified business and e… [+6432 chars]\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"source\": {\n" +
//            "            \"id\": null,\n" +
//            "            \"name\": \"Entrepreneur\"\n" +
//            "        },\n" +
//            "            \"author\": \"Robert Tuchman\",\n" +
//            "            \"title\": \"How Success Happened for Nick Brown, Co-Founder of Imaginary Ventures\",\n" +
//            "            \"description\": \"Nick Brown found success with the help of business partner Natalie Massenet.\",\n" +
//            "            \"url\": \"https://www.entrepreneur.com/article/411728\",\n" +
//            "            \"urlToImage\": \"https://assets.entrepreneur.com/content/3x2/2000/1641753241-NickBrown-ImaginaryVentures.jpg\",\n" +
//            "            \"publishedAt\": \"2022-01-09T18:50:00Z\",\n" +
//            "            \"content\": \"Nick Brown is co-founder and managing partner at Imaginary Ventures, a venture capital firm focused on the intersection of technology and retail. Founded in 2018 in partnership with former CEO of Net… [+2917 chars]\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"source\": {\n" +
//            "            \"id\": null,\n" +
//            "            \"name\": \"Entrepreneur\"\n" +
//            "        },\n" +
//            "            \"author\": \"Greg Smith\",\n" +
//            "            \"title\": \"Regaining Control of Your Sleep Life...From a (Recovering) Insomniac Entrepreneur\",\n" +
//            "            \"description\": \"I never had trouble leaving things at the office -- until I started building my own business.\",\n" +
//            "            \"url\": \"https://www.entrepreneur.com/article/404012\",\n" +
//            "            \"urlToImage\": \"https://assets.entrepreneur.com/content/3x2/2000/1642098877-shutterstock-1841485552.jpg\",\n" +
//            "            \"publishedAt\": \"2022-01-22T13:00:00Z\",\n" +
//            "            \"content\": \"Entrepreneurship goes hand-in-hand with a lot of things. Determination. Dreaming big. An embrace of failure and risk. And, in my personal experience: insomnia. \\r\\nIve found this is especially true for… [+7304 chars]\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"source\": {\n" +
//            "            \"id\": null,\n" +
//            "            \"name\": \"Entrepreneur\"\n" +
//            "        },\n" +
//            "            \"author\": \"Entrepreneur Store\",\n" +
//            "            \"title\": \"5 Best Password Managers for 2022\",\n" +
//            "            \"description\": \"Make password security a priority for your business.\",\n" +
//            "            \"url\": \"https://www.entrepreneur.com/article/412578\",\n" +
//            "            \"urlToImage\": \"https://assets.entrepreneur.com/content/3x2/2000/1641917428-Ent-PasswordManagersRoundup.jpeg\",\n" +
//            "            \"publishedAt\": \"2022-01-15T14:30:00Z\",\n" +
//            "            \"content\": \"You have enough on your plate as an entrepreneur, do you really want to have to remember all of your passwords, too? Writing them down in a notebook or sticky notes isn't exactly the most secure meth… [+2524 chars]\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"source\": {\n" +
//            "            \"id\": null,\n" +
//            "            \"name\": \"Entrepreneur\"\n" +
//            "        },\n" +
//            "            \"author\": \"Bhavik Sarkhedi\",\n" +
//            "            \"title\": \"Don't Underestimate the Power of Content Writing Services\",\n" +
//            "            \"description\": \"Business owners grossly undervalue quality content to save money.\",\n" +
//            "            \"url\": \"https://www.entrepreneur.com/article/399565\",\n" +
//            "            \"urlToImage\": \"https://assets.entrepreneur.com/content/3x2/2000/1640198317-shutterstock-1154106985.jpg\",\n" +
//            "            \"publishedAt\": \"2021-12-26T22:00:00Z\",\n" +
//            "            \"content\": \"Content is undeniably the soul of any form of consumer communication. But whether you take a straightforward or unforeseen approach, you need a content strategy to convey your message clearly. \\r\\nCont… [+4589 chars]\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"source\": {\n" +
//            "            \"id\": null,\n" +
//            "            \"name\": \"Entrepreneur\"\n" +
//            "        },\n" +
//            "            \"author\": \"Daniel Todd\",\n" +
//            "            \"title\": \"Passion, Grit, Resilience: The Formula for Success\",\n" +
//            "            \"description\": \"These three traits are crucial to business success.\",\n" +
//            "            \"url\": \"https://www.entrepreneur.com/article/401551\",\n" +
//            "            \"urlToImage\": \"https://assets.entrepreneur.com/content/3x2/2000/1639600619-GettyImages-964838980.jpg\",\n" +
//            "            \"publishedAt\": \"2021-12-30T16:00:00Z\",\n" +
//            "            \"content\": \"With anything you start, from a sport to a business, passion is the initial driving force behind the desire and ambition to create a plan of attack, but people can lose that passion without grit and … [+6355 chars]\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"source\": {\n" +
//            "            \"id\": null,\n" +
//            "            \"name\": \"Entrepreneur\"\n" +
//            "        },\n" +
//            "            \"author\": \"Stephanie Mojica\",\n" +
//            "            \"title\": \"How Podcast Interviews Can Help You Make More Money\",\n" +
//            "            \"description\": \"If you're not doing any for your business, you're potentially leaving money on the table.\",\n" +
//            "            \"url\": \"https://www.entrepreneur.com/article/410109\",\n" +
//            "            \"urlToImage\": \"https://assets.entrepreneur.com/content/3x2/2000/1642098711-shutterstock-1433396480.jpg\",\n" +
//            "            \"publishedAt\": \"2022-01-21T15:00:00Z\",\n" +
//            "            \"content\": \"When entrepreneurs think of getting into the media, they often think of magazines, newspapers, TV and radio.\\r\\nBut podcasts are a growing, and still often-overlooked, form of media that can dramatical… [+6044 chars]\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"source\": {\n" +
//            "            \"id\": null,\n" +
//            "            \"name\": \"Entrepreneur\"\n" +
//            "        },\n" +
//            "            \"author\": \"Gene Marks\",\n" +
//            "            \"title\": \"A Deepfake Phone Call Dupes An Employee Into Giving Away \$35 Million\",\n" +
//            "            \"description\": \"Think your business is too small to be fooled? Think again.\",\n" +
//            "            \"url\": \"https://www.entrepreneur.com/article/414109\",\n" +
//            "            \"urlToImage\": \"https://assets.entrepreneur.com/content/3x2/2000/1642699759-GettyImages-1258096393.jpg\",\n" +
//            "            \"publishedAt\": \"2022-01-24T19:00:00Z\",\n" +
//            "            \"content\": \"\\\"Hi Susan, it's Gene. Sorry for calling after hours, but I'm travelling. Can you please transfer \$35,000 from our business checking account to a new supplier for a deposit on a job? Here's their bank… [+4114 chars]\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"source\": {\n" +
//            "            \"id\": null,\n" +
//            "            \"name\": \"ReadWrite\"\n" +
//            "        },\n" +
//            "            \"author\": \"Vinod Janapala\",\n" +
//            "            \"title\": \"10 Steps to Start and Build a Successful Business\",\n" +
//            "            \"description\": \"Starting a business can be a daunting task. Even if you do have a business idea — it is only one of the first steps to starting a new business. A lot can go wrong if you do not plan well and implement the plan even better. But, as long as you follow certain b…\",\n" +
//            "            \"url\": \"https://readwrite.com/2022/01/12/steps-to-start-and-build-a-successful-business/\",\n" +
//            "            \"urlToImage\": \"https://images.readwrite.com/wp-content/uploads/2021/11/Build-a-Successful-Business.jpg\",\n" +
//            "            \"publishedAt\": \"2022-01-12T17:27:55Z\",\n" +
//            "            \"content\": \"Starting a business can be a daunting task. Even if you do have a business idea it is only one of the first steps to starting a new business. A lot can go wrong if you do not plan well and implement … [+7109 chars]\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"source\": {\n" +
//            "            \"id\": \"business-insider\",\n" +
//            "            \"name\": \"Business Insider\"\n" +
//            "        },\n" +
//            "            \"author\": \"insider@insider.com (Jen Glantz)\",\n" +
//            "            \"title\": \"How my Capital One Spark Miles for Business credit card helps me grow my small business and saves me money every month\",\n" +
//            "            \"description\": \"From redeeming miles for travel to excellent fraud protection, the Capital One Spark Miles for Business card's benefits have been invaluable.\",\n" +
//            "            \"url\": \"https://www.businessinsider.com/personal-finance/capital-one-spark-miles-business-credit-card-save-money-2022-1\",\n" +
//            "            \"urlToImage\": \"https://i.insider.com/61d88aa93ef33f001823d34f?width=1200&format=jpeg\",\n" +
//            "            \"publishedAt\": \"2022-01-07T20:32:16Z\",\n" +
//            "            \"content\": \"This post contains links to products from our advertisers, and we may be compensated when you click on these links. Our recommendations and advice are ours alone, and have not been reviewed by any is… [+5250 chars]\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"source\": {\n" +
//            "            \"id\": null,\n" +
//            "            \"name\": \"The Guardian\"\n" +
//            "        },\n" +
//            "            \"author\": \"Adrian Horton\",\n" +
//            "            \"title\": \"Colbert on Trump’s ‘pattern of possible fraud’: ‘Certainly more believable than a pattern of business’\",\n" +
//            "            \"description\": \"Late-night hosts dig into the court filing outlining possible fraudulent business practices by the former presidentLate-night hosts lapped up details of financial misconduct by the Trump family on Wednesday evening, a day after the office of New York attorney…\",\n" +
//            "            \"url\": \"https://amp.theguardian.com/culture/2022/jan/20/stephen-colbert-donald-trump-late-night-tv\",\n" +
//            "            \"urlToImage\": \"https://i.guim.co.uk/img/media/9d81719d0ae9c9e87e0d75ceade6953c4991c911/176_121_2547_1528/master/2547.jpg?width=1200&height=630&quality=85&auto=format&fit=crop&overlay-align=bottom%2Cleft&overlay-width=100p&overlay-base64=L2ltZy9zdGF0aWMvb3ZlcmxheXMvdGctZGVmYXVsdC5wbmc&enable=upscale&s=e93fd0637696c7fd63d790307ecd5eb9\",\n" +
//            "            \"publishedAt\": \"2022-01-20T15:23:03Z\",\n" +
//            "            \"content\": \"Stephen Colbert\\r\\nLate-night hosts lapped up details of financial misconduct by the Trump family on Wednesday evening, a day after the office of the New York attorney general, Letitia James, told a co… [+4413 chars]\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"source\": {\n" +
//            "            \"id\": \"reuters\",\n" +
//            "            \"name\": \"Reuters\"\n" +
//            "        },\n" +
//            "            \"author\": null,\n" +
//            "            \"title\": \"Massachusetts top court weighs insurance coverage for COVID business losses - Reuters\",\n" +
//            "            \"description\": \"Justices on Massachusetts' top court on Friday questioned why it should diverge from courts nationally that have found businesses are not entitled to insurance coverage for losses caused by COVID-19 as it heard an appeal by three restaurants.\",\n" +
//            "            \"url\": \"https://www.reuters.com/legal/government/massachusetts-top-court-weighs-insurance-coverage-covid-business-losses-2022-01-07/\",\n" +
//            "            \"urlToImage\": \"https://www.reuters.com/resizer/bG9JzZI3agFk5b0u2ajRDK3Fxfw=/728x381/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/WTCM3A2SQFPSLDLGPM3Z7EZ3CU.jpg\",\n" +
//            "            \"publishedAt\": \"2022-01-07T20:40:00Z\",\n" +
//            "            \"content\": \"The company and law firm names shown above are generated automatically based on the text of the article. We are improving this feature as we continue to test and develop in beta. We welcome feedback,… [+3281 chars]\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"source\": {\n" +
//            "            \"id\": \"reuters\",\n" +
//            "            \"name\": \"Reuters\"\n" +
//            "        },\n" +
//            "            \"author\": null,\n" +
//            "            \"title\": \"SK Hynix completes first phase of \$9 bln Intel NAND business buy - Reuters\",\n" +
//            "            \"description\": \"South Korea's SK Hynix Inc <a href=\\\"https://www.reuters.com/companies/000660.KS\\\" target=\\\"_blank\\\">(000660.KS)</a> said it had completed the first phase of its acquisition of Intel Corp's <a href=\\\"https://www.reuters.com/companies/INTC.O\\\" target=\\\"_blank\\\">(INTC.…\",\n" +
//            "            \"url\": \"https://www.reuters.com/technology/sk-hynix-completes-first-phase-9-bln-intel-nand-business-buy-2021-12-29/\",\n" +
//            "            \"urlToImage\": \"https://www.reuters.com/resizer/mp9p8B_Guuf1IQSoHUFodxahkxo=/1200x628/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/IIGX3M5SBZITHMS3M7MRU3LGHM.jpg\",\n" +
//            "            \"publishedAt\": \"2021-12-29T23:30:00Z\",\n" +
//            "            \"content\": \"SEOUL, Dec 30 (Reuters) - South Korea's SK Hynix Inc (000660.KS) said it had completed the first phase of its acquisition of Intel Corp's (INTC.O) NAND flash memory chip business, after it received r… [+1557 chars]\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"source\": {\n" +
//            "            \"id\": \"reuters\",\n" +
//            "            \"name\": \"Reuters\"\n" +
//            "        },\n" +
//            "            \"author\": null,\n" +
//            "            \"title\": \"In Egypt, lending apps boost cash-strapped women business owners - Reuters\",\n" +
//            "            \"description\": \"Nagat Mohamed was in dire straits. After sales at her clothes shop in Egypt's Nile Delta plummeted, she took out a loan from a microfinance company to keep the business going – but did not earn enough to pay that back either.\",\n" +
//            "            \"url\": \"https://www.reuters.com/markets/rates-bonds/egypt-lending-apps-boost-cash-strapped-women-business-owners-2021-12-30/\",\n" +
//            "            \"urlToImage\": \"https://www.reuters.com/pf/resources/images/reuters/reuters-default.png?d=66\",\n" +
//            "            \"publishedAt\": \"2021-12-30T03:05:00Z\",\n" +
//            "            \"content\": \"CAIRO, Dec 30 (Thomson Reuters Foundation) - Nagat Mohamed was in dire straits. After sales at her clothes shop in Egypt's Nile Delta plummeted, she took out a loan from a microfinance company to kee… [+5559 chars]\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"source\": {\n" +
//            "            \"id\": \"reuters\",\n" +
//            "            \"name\": \"Reuters\"\n" +
//            "        },\n" +
//            "            \"author\": \"Reuters Staff\",\n" +
//            "            \"title\": \"Office Depot parent delays spin-off after new offer for consumer business - Reuters.com\",\n" +
//            "            \"description\": \"ODP Corp said on Friday it had decided to delay its previously announced plans to split into two public companies to mull over sale offers for its consumer business, sending its shares up 10%.\",\n" +
//            "            \"url\": \"https://www.reuters.com/article/odp-divestiture-idUSKBN2JO1B1\",\n" +
//            "            \"urlToImage\": \"https://static.reuters.com/resources/r/?m=02&d=20220114&t=2&i=1587808411&r=LYNXMPEI0D0LN&w=800\",\n" +
//            "            \"publishedAt\": \"2022-01-14T14:01:36Z\",\n" +
//            "            \"content\": \"By Reuters Staff\\r\\nFILE PHOTO: An Office Depot Inc store is shown in Encinitas, California, U.S., May 8, 2017. REUTERS/Mike Blake\\r\\n(Reuters) - ODP Corp said on Friday it had decided to delay its previ… [+1411 chars]\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"source\": {\n" +
//            "            \"id\": \"reuters\",\n" +
//            "            \"name\": \"Reuters\"\n" +
//            "        },\n" +
//            "            \"author\": null,\n" +
//            "            \"title\": \"Delivery Hero expects food delivery business breakeven in second half of 2022 - Reuters\",\n" +
//            "            \"description\": \"Germany's Delivery Hero SE <a href=\\\"https://www.reuters.com/companies/DHER.DE\\\" target=\\\"_blank\\\">(DHER.DE)</a> expects its food delivery business to break even during the second half of 2022, as demand soars with more people eating in since the pandemic began.\",\n" +
//            "            \"url\": \"https://www.reuters.com/markets/europe/delivery-hero-expects-food-delivery-business-breakeven-second-half-2022-2022-01-10/\",\n" +
//            "            \"urlToImage\": \"https://www.reuters.com/resizer/FvWFeVYhr35TmQZrZDOQGZ0I3Us=/1200x628/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/G2H7VNMNOFNUBFIVZTD7G23U7I.jpg\",\n" +
//            "            \"publishedAt\": \"2022-01-10T23:06:00Z\",\n" +
//            "            \"content\": \"The Delivery Hero's logo is pictured at its headquarters in Berlin, Germany, August 18, 2020. REUTERS/Fabrizio BenschJan 10 (Reuters) - Germany's Delivery Hero SE (DHER.DE) expects its food delivery … [+945 chars]\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"source\": {\n" +
//            "            \"id\": \"reuters\",\n" +
//            "            \"name\": \"Reuters\"\n" +
//            "        },\n" +
//            "            \"author\": null,\n" +
//            "            \"title\": \"NY attorney general details possible fraud at Donald Trump's family business - Reuters\",\n" +
//            "            \"description\": \"New York state's attorney general has accused Donald Trump's family business of repeatedly misrepresenting the value of its assets to obtain financial benefits, citing what it said was significant new evidence of possible fraud.\",\n" +
//            "            \"url\": \"https://www.reuters.com/world/us/ny-attorney-general-details-possible-fraud-donald-trumps-family-business-2022-01-19/\",\n" +
//            "            \"urlToImage\": \"https://www.reuters.com/resizer/fDxD86DEATnevpmJI34Ins8DTAk=/1200x628/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/IC6CBPMN6NI2TD7A4SEB6TVJOY.jpg\",\n" +
//            "            \"publishedAt\": \"2022-01-19T15:40:00Z\",\n" +
//            "            \"content\": \"NEW YORK, Jan 19 (Reuters) - New York state's attorney general has accused Donald Trump's family business of repeatedly misrepresenting the value of its assets to obtain financial benefits, citing wh… [+5158 chars]\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"source\": {\n" +
//            "            \"id\": \"reuters\",\n" +
//            "            \"name\": \"Reuters\"\n" +
//            "        },\n" +
//            "            \"author\": null,\n" +
//            "            \"title\": \"China c.bank issues draft rules to cap banks' commercial bill financing business - Reuters\",\n" +
//            "            \"description\": \"China's central bank published draft rules on Friday to strengthen risk management over the use of commercial bills, and set up caps for banks' commercial bills financing business, according to a statement.\",\n" +
//            "            \"url\": \"https://www.reuters.com/markets/rates-bonds/china-cbank-issues-draft-rules-cap-banks-commercial-bill-financing-business-2022-01-14/\",\n" +
//            "            \"urlToImage\": \"https://www.reuters.com/pf/resources/images/reuters/reuters-default.png?d=67\",\n" +
//            "            \"publishedAt\": \"2022-01-14T10:11:00Z\",\n" +
//            "            \"content\": \"BEIJING, Jan 14 (Reuters) - China's central bank published draft rules on Friday to strengthen risk management over the use of commercial bills, and set up caps for banks' commercial bills financing … [+848 chars]\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "            \"source\": {\n" +
//            "            \"id\": \"reuters\",\n" +
//            "            \"name\": \"Reuters\"\n" +
//            "        },\n" +
//            "            \"author\": null,\n" +
//            "            \"title\": \"U.N. chief urges business to help poor nations in 'hour of need' - Reuters\",\n" +
//            "            \"description\": \"U.N. Secretary-General Antonio Guterres appealed to business leaders on Monday to support developing countries \\\"in their hour of need\\\" with access to COVID-19 vaccines, help to combat the climate crisis and reform of the global financial system.\",\n" +
//            "            \"url\": \"https://www.reuters.com/world/un-chief-urges-business-help-poor-nations-hour-need-2022-01-17/\",\n" +
//            "            \"urlToImage\": \"https://www.reuters.com/resizer/iMqx4u3gr9lnxasW2JDOgdzoVxM=/1200x628/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/EAJJQ6H3XJIWRFCFSOGRANPCYE.jpg\",\n" +
//            "            \"publishedAt\": \"2022-01-17T17:49:00Z\",\n" +
//            "            \"content\": \"Jan 17 (Reuters) - U.N. Secretary-General Antonio Guterres appealed to business leaders on Monday to support developing countries \\\"in their hour of need\\\" with access to COVID-19 vaccines, help to com… [+1919 chars]\"\n" +
//            "        }\n" +
//            "        ]\n" +
//            "    }"
//    val sourceType = object : TypeToken<NewsRequest>() {}.type
//    return Gson().fromJson(data, sourceType)
//}