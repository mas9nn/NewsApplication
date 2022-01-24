package com.example.newsapp.presentation.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.model.NewsRequest
import com.example.newsapp.util.Resource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    private val _state: MutableLiveData<HeadlinesState> = MutableLiveData(HeadlinesState())
    val state: LiveData<HeadlinesState> = _state

    var searchQuery = ""

    fun getHeadlines(page: Int) {
//        _state.value = HeadlinesState(
//            result = toNews(
//                "{\n" +
//                        "  \"status\": \"ok\",\n" +
//                        "  \"totalResults\": 1000,\n" +
//                        "  \"articles\": [\n" +
//                        "    {\n" +
//                        "      \"source\": {\n" +
//                        "        \"id\": \"google-news\",\n" +
//                        "        \"name\": \"Google News\"\n" +
//                        "      },\n" +
//                        "      \"author\": null,\n" +
//                        "      \"title\": \"Tipo Tubo De La Descarga De Corona Generador De Ozono Mercado 2021 (Covid-19 De Actualización) Retos De Futuro, Las Estadísticas De Crecimiento Y La Previsión Para El Año 2031 – Influencers Web - Influencers Web\",\n" +
//                        "      \"description\": null,\n" +
//                        "      \"url\": \"https://news.google.com/__i/rss/rd/articles/CBMiZ2h0dHBzOi8vd3d3LmluZmx1ZW5jZXJzd2ViLmNvbS9nbG9iYWwtZ2VuZXJhZG9yLWRlLW96b25vLWRlLWRlc2NhcmdhLWNvcm9uYS10aXBvLXR1Ym8tbWVyY2Fkby1udWVzdHJvcy_SAQA?oc=5\",\n" +
//                        "      \"urlToImage\": null,\n" +
//                        "      \"publishedAt\": \"2022-01-24T10:32:45Z\",\n" +
//                        "      \"content\": null\n" +
//                        "    },\n" +
//                        "    {\n" +
//                        "      \"source\": {\n" +
//                        "        \"id\": null,\n" +
//                        "        \"name\": \"Finanzen.at\"\n" +
//                        "      },\n" +
//                        "      \"author\": null,\n" +
//                        "      \"title\": \"Börse Frankfurt-News: Starke Nerven gefragt (Wochenausblick) - finanzen.at\",\n" +
//                        "      \"description\": \"FRANKFURT (DEUTSCHE-BOERSE AG) - 24. Januar 2022. Omikron scheint an den Märkten abgehakt, jetzt sind Inflation und höhere Leitzinsen die größten ...\",\n" +
//                        "      \"url\": \"https://www.finanzen.at/nachrichten/aktien/boerse-frankfurt-news-starke-nerven-gefragt-wochenausblick-1031121983\",\n" +
//                        "      \"urlToImage\": \"https://www.finanzen.at/Images/FacebookIcon.jpg\",\n" +
//                        "      \"publishedAt\": \"2022-01-24T10:30:38Z\",\n" +
//                        "      \"content\": \"FRANKFURT (DEUTSCHE-BOERSE AG) - 24. Januar 2022. Omikron scheint an den Märkten abgehakt, jetzt sind Inflation und höhere Leitzinsen die größten Belastungsfaktoren. Ob die starken Kursverluste noch … [+5865 chars]\"\n" +
//                        "    },\n" +
//                        "    {\n" +
//                        "      \"source\": {\n" +
//                        "        \"id\": null,\n" +
//                        "        \"name\": \"Institutional-money.com\"\n" +
//                        "      },\n" +
//                        "      \"author\": \"Institutional Money\",\n" +
//                        "      \"title\": \"Wachstum und Zinsanstieg? Ex-Bond-König Bill Gross bezweifelt beides - Institutional-Money\",\n" +
//                        "      \"description\": \"Der ehemalige Bond-König Bill Gross erweist sich erneut als ausgeprägter \\\"Contrarian\\\". Der Gründer der US-Fondsgesellschaft Pimco erwartet nicht nur einen deutlichen Rückgang des US-Wachstums, er rechnet auch mit nur wenigen Zinsschritten seitens der amerikan…\",\n" +
//                        "      \"url\": \"https://www.institutional-money.com/news/maerkte/headline/wachstum-und-zinsanstieg-altmeister-bill-gross-bezweifelt-beides-212967/\",\n" +
//                        "      \"urlToImage\": \"https://www.institutional-money.com/content/fpim/uploads/news/2/a/c/1643016979_gross.jpg\",\n" +
//                        "      \"publishedAt\": \"2022-01-24T10:26:00Z\",\n" +
//                        "      \"content\": null\n" +
//                        "    },\n" +
//                        "    {\n" +
//                        "      \"source\": {\n" +
//                        "        \"id\": null,\n" +
//                        "        \"name\": \"Finanzen.at\"\n" +
//                        "      },\n" +
//                        "      \"author\": null,\n" +
//                        "      \"title\": \"Rückstellungen : Commerzbank-Aktie unter Druck: Polnische Kredite belasten - Trotzdem Nettogewinn für 202.. - finanzen.at\",\n" +
//                        "      \"description\": \"Die Commerzbank stellt sich wegen der Unsicherheit rund um Fremdwährungskredite in Polen auf eine weitere Belastung ein.\",\n" +
//                        "      \"url\": \"https://www.finanzen.at/nachrichten/aktien/commerzbank-aktie-unter-druck-polnische-kredite-belasten-trotzdem-nettogewinn-fuer-2021-erwartet-1031119714\",\n" +
//                        "      \"urlToImage\": \"https://images.finanzen.at/images/unsortiert/commerzbank_by_mf_org_660.jpg\",\n" +
//                        "      \"publishedAt\": \"2022-01-24T10:23:00Z\",\n" +
//                        "      \"content\": \"Die Commerzbank stellt sich wegen der Unsicherheit rund um Fremdwährungskredite in Polen auf eine weitere Belastung ein.\\r\\nDie polnische Konzerntochter mBank habe Ende 2021 zusätzliche Rückstellungen … [+1232 chars]\"\n" +
//                        "    },\n" +
//                        "    {\n" +
//                        "      \"source\": {\n" +
//                        "        \"id\": null,\n" +
//                        "        \"name\": \"Estrategiasdeinversion.com\"\n" +
//                        "      },\n" +
//                        "      \"author\": \"Estrategias de Inversión\",\n" +
//                        "      \"title\": \"Banco Santander apoya la internacionalización de las empresas españolas con 22.300 millones en 2021 - Estrategias de Inversión\",\n" +
//                        "      \"description\": \"La entidad bancaria ayuda a más de 140.000 empresas españolas a desarrollar su actividad internacional. Asimismo, Banco Santander ha reforzado sus canales digitales para mejorar el seguimiento de pagos internaciones online, el seguimiento de mercancías y la g…\",\n" +
//                        "      \"url\": \"https://www.estrategiasdeinversion.com/actualidad/noticias/empresas/banco-santander-apoya-la-internacionalizacion-n-495387\",\n" +
//                        "      \"urlToImage\": \"https://www.estrategiasdeinversion.com/uploads/noticias_redaccion/Portada2/banco_santander.jpg\",\n" +
//                        "      \"publishedAt\": \"2022-01-24T10:20:00Z\",\n" +
//                        "      \"content\": \"Banco Santander financió el negocio internacional de las empresas españolas con 22.300 millones de euros en 2021, un 12% más que el año anterior. Este aumento indica que están recuperando la activida… [+2175 chars]\"\n" +
//                        "    },\n" +
//                        "    {\n" +
//                        "      \"source\": {\n" +
//                        "        \"id\": null,\n" +
//                        "        \"name\": \"Industriemagazin.at\"\n" +
//                        "      },\n" +
//                        "      \"author\": \"\",\n" +
//                        "      \"title\": \"INDUSTRIEMAGAZIN | Porr erhält Bestnote in Klimwandel Rating - Industriemagazin\",\n" +
//                        "      \"description\": \"Das Carbon Disclosure Project stellt Porr heuer die Topnote A aus. Für den Baukonzern soll es in Sachen Klimaschutz so weitergehen.\",\n" +
//                        "      \"url\": \"https://industriemagazin.at/aktuell/porr-erhaelt-bestnote-in-klimwandel-rating/\",\n" +
//                        "      \"urlToImage\": \"https://weka-alps-media.s3.amazonaws.com/media/industriemagazin/_1200x630_crop_center-center_82_none/46247.jpg?mtime=1629976279\",\n" +
//                        "      \"publishedAt\": \"2022-01-24T10:17:41Z\",\n" +
//                        "      \"content\": \"Mehr als 13.000 Unternehmen weltweit haben heuer am Rating des Carbon Disclosure Project (CDP) teilgenommen. Darunter hat Porr im Bereich Klimawandel erstmals die Topnote A- erreicht.\\r\\nUns freut, das… [+2209 chars]\"\n" +
//                        "    },\n" +
//                        "    {\n" +
//                        "      \"source\": {\n" +
//                        "        \"id\": null,\n" +
//                        "        \"name\": \"Industriemagazin.at\"\n" +
//                        "      },\n" +
//                        "      \"author\": \"\",\n" +
//                        "      \"title\": \"INDUSTRIEMAGAZIN | Deutsche Elektroindustrie rechnet mit Zuwachs - Industriemagazin\",\n" +
//                        "      \"description\": \"Die Elektrobranche der Industrie in Deutschland rechnet mit Zuwächsen. Was die Aussichten dennoch trübt, lesen Sie hier.\",\n" +
//                        "      \"url\": \"https://industriemagazin.at/aktuell/deutsche-elektroindustrie-rechnet-mit-zuwachs/\",\n" +
//                        "      \"urlToImage\": \"https://weka-alps-media.s3.amazonaws.com/media/industriemagazin/_1200x630_crop_center-center_82_none/AdobeStock_478301028.jpeg?mtime=1643019044\",\n" +
//                        "      \"publishedAt\": \"2022-01-24T10:17:41Z\",\n" +
//                        "      \"content\": \"Die deutsche Elektroindustrie erwartet heuer kräftige Zuwächse, wenngleich weniger stark als 2021. Die Produktion dürfte um vier Prozent steigen, teilte der Branchenverband ZVEI am Montag mit. Die Pr… [+828 chars]\"\n" +
//                        "    },\n" +
//                        "    {\n" +
//                        "      \"source\": {\n" +
//                        "        \"id\": null,\n" +
//                        "        \"name\": \"Boerse-social.com\"\n" +
//                        "      },\n" +
//                        "      \"author\": null,\n" +
//                        "      \"title\": \"Uranpreis im Rallyemodus - Bill Gates und Warren Buffet steigen ein. Sensationelle Übernahme. Massives Kaufsignal. Diese Uran-Aktie jetzt kaufen nach 1.324% mit Isoenergy (ISO.V), 1.390% mit Uranium Energy (UEC), 3.496% mit NexGen Energy (NX - Boerse Social Network\",\n" +
//                        "      \"description\": \"Uranpreis im Rallyemodus - Bill Gates und Warren Buffet steigen ein. Sensationelle Übernahme. Massives Kaufsignal. Diese Uran-Aktie jetzt kaufen nach 1.324% mit Isoenergy ISO.V  1.390% Uranium Energy UEC  3.496% NexGen NXE 8.050% EnCore EU.V\",\n" +
//                        "      \"url\": \"https://boerse-social.com/2022/01/24/uranpreis_im_rallyemodus_-_bill_gates_und_warren_buffet_steigen_ein_sensationelle_abernahme_massives_kaufsignal_diese_uran-aktie_jetzt_kaufen_nach_1324_mit_isoenergy_isov_1390_mit_uranium_energy_uec_3496_mit_nexgen_energy_nxe_und_und_8050_mit_encore_energy_euv\",\n" +
//                        "      \"urlToImage\": \"http://www.boerse-social.com/images/logos/bsn_hoch_weiss.jpg\",\n" +
//                        "      \"publishedAt\": \"2022-01-24T10:14:55Z\",\n" +
//                        "      \"content\": \"Uranpreis im Rallyemodus - Bill Gates und Warren Buffet steigen ein. Sensationelle Übernahme. Massives Kaufsignal. Diese Uran-Aktie jetzt kaufen nach 1.324% mit Isoenergy (ISO.V), 1.390% mit Uranium… [+65113 chars]\"\n" +
//                        "    },\n" +
//                        "    {\n" +
//                        "      \"source\": {\n" +
//                        "        \"id\": null,\n" +
//                        "        \"name\": \"Trend.at\"\n" +
//                        "      },\n" +
//                        "      \"author\": null,\n" +
//                        "      \"title\": \"Eni bereitet Börsengang von Tochter Vaar Energi vor - trend.at\",\n" +
//                        "      \"description\": \"Eni will mit Mehrheitsbeteiligung von \\\"50-plus\\\" an Vaar beteiligt bleiben\",\n" +
//                        "      \"url\": \"https://www.trend.at/news/eni-boersengang-tochter-vaar-energi-12386281\",\n" +
//                        "      \"urlToImage\": null,\n" +
//                        "      \"publishedAt\": \"2022-01-24T10:10:33Z\",\n" +
//                        "      \"content\": \"Der italienische Energieversorger Eni und die norwegische Beteiligungsfirma HitecVision wollen ihr Gemeinschaftsunternehmen Vaar Energi an die Börse in Oslo bringen. Das teilten die beiden Unternehme… [+435 chars]\"\n" +
//                        "    },\n" +
//                        "    {\n" +
//                        "      \"source\": {\n" +
//                        "        \"id\": \"google-news\",\n" +
//                        "        \"name\": \"Google News\"\n" +
//                        "      },\n" +
//                        "      \"author\": null,\n" +
//                        "      \"title\": \"Mercado global de La Cabeza De La Bobina Con análisis estratégico de la industria y panorama competitivo durante el período de pronóstico 2022-2031 – Influencers Web - Influencers Web\",\n" +
//                        "      \"description\": null,\n" +
//                        "      \"url\": \"https://news.google.com/__i/rss/rd/articles/CBMiaWh0dHBzOi8vd3d3LmluZmx1ZW5jZXJzd2ViLmNvbS9tZXJjYWRvLWdsb2JhbC1kZS1sYS1jYWJlemEtZGUtbGEtYm9iaW5hLXBlcnNwZWN0aXZhLWRlLWxhLWluZHVzdHJpYS0yMDIyL9IBAA?oc=5\",\n" +
//                        "      \"urlToImage\": null,\n" +
//                        "      \"publishedAt\": \"2022-01-24T10:07:17Z\",\n" +
//                        "      \"content\": null\n" +
//                        "    },\n" +
//                        "    {\n" +
//                        "      \"source\": {\n" +
//                        "        \"id\": null,\n" +
//                        "        \"name\": \"Stcn.com\"\n" +
//                        "      },\n" +
//                        "      \"author\": \"阙福生\",\n" +
//                        "      \"title\": \"数据复盘｜46股获主力加仓超亿元龙虎榜机构抢筹赣锋锂业等 - 证券时报\",\n" +
//                        "      \"description\": \"今日有1662只个股获主力资金净流入，其中，46股获主力资金净流入超1亿元。赣锋锂业获主力资金净流入最多，净流入金额为11.87亿元；主力资金净流入居前的个股还有天齐锂业、盐湖股份、欣旺达等。\",\n" +
//                        "      \"url\": \"http://www.stcn.com/stock/djjd/202201/t20220124_4105224.html\",\n" +
//                        "      \"urlToImage\": null,\n" +
//                        "      \"publishedAt\": \"2022-01-24T10:07:00Z\",\n" +
//                        "      \"content\": \"124501%3524.110.04%3580.114081.800.37%5061.513056.430.72%2017.19501307.741.34%4168641.61\\r\\n1.\\r\\nWind166.973242.9530034.3911.35\\r\\n28712.739.8852.22167.7519.0718.2315.1713.698.61\\r\\n166246111.875.524.973.94… [+217 chars]\"\n" +
//                        "    },\n" +
//                        "    {\n" +
//                        "      \"source\": {\n" +
//                        "        \"id\": null,\n" +
//                        "        \"name\": \"Eastmoney.com\"\n" +
//                        "      },\n" +
//                        "      \"author\": null,\n" +
//                        "      \"title\": \"北向资金持续流入在大金融板块现分歧增持这只“银行茅”居首（名单） - 东方财富网\",\n" +
//                        "      \"description\": \"周一大盘震荡反弹。截至收盘，沪指微涨0.04%，深成指涨0.37%，创业板指反弹至5日均线附近，收涨0.72%。沪深两市今日成交额8642亿，较上个交易日缩量1203亿，量能萎缩明显。盘面上，锂电产业链(锂矿、电解液等)、数字货币、HJT电池领涨；食品饮料、医药等消费以及大金融表现弱势。北向资金延续净买入态势。\",\n" +
//                        "      \"url\": \"http://stock.eastmoney.com/a/202201242259229980.html\",\n" +
//                        "      \"urlToImage\": null,\n" +
//                        "      \"publishedAt\": \"2022-01-24T10:06:00Z\",\n" +
//                        "      \"content\": null\n" +
//                        "    },\n" +
//                        "    {\n" +
//                        "      \"source\": {\n" +
//                        "        \"id\": null,\n" +
//                        "        \"name\": \"EL PAÍS\"\n" +
//                        "      },\n" +
//                        "      \"author\": \"Santiago Millán Alonso\",\n" +
//                        "      \"title\": \"Vodafone se dispara en Bolsa ante posibles fusiones y tira de Telefónica y las telecos - Cinco Días\",\n" +
//                        "      \"description\": \"Llega a avanzar más de un 7% ante sus negociaciones con Iliad en Italia y Hutchison en Reino Unido El grupo español lidera el Ibex 35\",\n" +
//                        "      \"url\": \"https://cincodias.elpais.com/cincodias/2022/01/24/companias/1643015006_621978.html\",\n" +
//                        "      \"urlToImage\": \"https://d500.epimg.net/cincodias/imagenes/2022/01/24/companias/1643015006_621978_1643015129_rrss_normal.jpg\",\n" +
//                        "      \"publishedAt\": \"2022-01-24T10:05:39Z\",\n" +
//                        "      \"content\": \"Vodafone ha comenzado la semana con fuertes subidas en Bolsa ante posibles fusiones en mercados claves como Italia y Reino Unido, y ha tirado en el mercado de otros competidores como Telefónica.\\r\\nAsí… [+2461 chars]\"\n" +
//                        "    },\n" +
//                        "    {\n" +
//                        "      \"source\": {\n" +
//                        "        \"id\": null,\n" +
//                        "        \"name\": \"Finanzen.at\"\n" +
//                        "      },\n" +
//                        "      \"author\": null,\n" +
//                        "      \"title\": \"Umsatz- und Gewinndelle : Philips-Aktie tiefrot: Philips will nach schwachem Jahr 2022 wieder wachsen - finanzen.at\",\n" +
//                        "      \"description\": \"Der Medizintechnikkonzern Philips will nach der Umsatz- und Gewinndelle im vergangenen Jahr 2022 wieder wachsen.\",\n" +
//                        "      \"url\": \"https://www.finanzen.at/nachrichten/aktien/philips-aktie-tiefrot-philips-will-nach-schwachem-jahr-2022-wieder-wachsen-1031121614\",\n" +
//                        "      \"urlToImage\": \"https://images.finanzen.at/images/unsortiert/philips-alexander-tihonov-9390.jpg\",\n" +
//                        "      \"publishedAt\": \"2022-01-24T10:05:00Z\",\n" +
//                        "      \"content\": \"Der Medizintechnikkonzern Philips will nach der Umsatz- und Gewinndelle im vergangenen Jahr 2022 wieder wachsen.\\r\\nSo geht das Management um Konzernchef Frans van Houten von einem Umsatzplus aus eigen… [+1927 chars]\"\n" +
//                        "    },\n" +
//                        "    {\n" +
//                        "      \"source\": {\n" +
//                        "        \"id\": null,\n" +
//                        "        \"name\": \"Ice.co.il\"\n" +
//                        "      },\n" +
//                        "      \"author\": \"מערכת Ice\",\n" +
//                        "      \"title\": \"שופרסל תסבול מעזיבת המנכ\\\"ל איציק אברכהן? \\\"חדשות שליליות\\\" - אייס\",\n" +
//                        "      \"description\": \"שירה אחיעז, אנליסטית קימעונאות, IBI בית השקעות מנתחת: \\\"הוביל מהלכים משמעותיים להתפתחות וצמיחת החברה, פתיחת החקירה של הרשות לתחרות בחודש נובמבר האחרון לצד חילוקי דעות עם היו\\\"ר יקי ודמני האיצו את מועד פרישתו\\\" ומי יחליפו בתפקיד?\",\n" +
//                        "      \"url\": \"http://www.ice.co.il/finance/news/article/841794\",\n" +
//                        "      \"urlToImage\": \"https://img.ice.co.il/giflib/news/4ice29112021.jpg\",\n" +
//                        "      \"publishedAt\": \"2022-01-24T10:05:00Z\",\n" +
//                        "      \"content\": \"\\\" ? \\\" \\\"\\r\\n( ', freepik)\\r\\n\\\" ??  \\\" , . , \\\" , 2021 \\\" , \\\" . , , \\\"  BE  , \\\" .\"\n" +
//                        "    },\n" +
//                        "    {\n" +
//                        "      \"source\": {\n" +
//                        "        \"id\": null,\n" +
//                        "        \"name\": \"Walla.co.il\"\n" +
//                        "      },\n" +
//                        "      \"author\": \"וואלה! כסף\",\n" +
//                        "      \"title\": \"דיסקונט: ראש חדש לחטיבה הבנקאית - וואלה! כסף\",\n" +
//                        "      \"description\": \"יקבל לאחריותו את ניהול הבנקאות הקמעונאית הכוללת את מערך הסניפים, מערך המשכנתאות, מוקדי שירות הלקוחות והבנקאות פרטית הישראלית והבינלאומית\",\n" +
//                        "      \"url\": \"https://finance.walla.co.il/item/3484670\",\n" +
//                        "      \"urlToImage\": \"https://images.wcdn.co.il/f_auto,q_auto,w_1200,t_54/3/2/9/2/3292975-46.jpg\",\n" +
//                        "      \"publishedAt\": \"2022-01-24T10:02:00Z\",\n" +
//                        "      \"content\": \"walla_ssr_page_has_been_loaded_successfully\"\n" +
//                        "    },\n" +
//                        "    {\n" +
//                        "      \"source\": {\n" +
//                        "        \"id\": null,\n" +
//                        "        \"name\": \"Enmovimientorevista.com\"\n" +
//                        "      },\n" +
//                        "      \"author\": \"alfonso\",\n" +
//                        "      \"title\": \"Artroplastia Mercado Análisis detallado centrado en la aplicación, tipos y perspectiva regional 2032 | DuPuy Orthopaedics, Stryker Corporation - En Movimiento Revista - En Movimiento Revista\",\n" +
//                        "      \"description\": \"El estudio, “Mercado global de Artroplastia” demuestra datos integrales de esta empresa de Artroplastia que...\",\n" +
//                        "      \"url\": \"https://enmovimientorevista.com/artroplastia-mercado-estudio-de-cadena-al-2032/\",\n" +
//                        "      \"urlToImage\": null,\n" +
//                        "      \"publishedAt\": \"2022-01-24T10:01:20Z\",\n" +
//                        "      \"content\": \"El estudio, “Mercado global de Artroplastia” demuestra datos integrales de esta empresa de Artroplastia que examina el tamaño del mercado y estima el estudio de mercado durante el período previsto 20… [+5683 chars]\"\n" +
//                        "    },\n" +
//                        "    {\n" +
//                        "      \"source\": {\n" +
//                        "        \"id\": null,\n" +
//                        "        \"name\": \"Expansion.com\"\n" +
//                        "      },\n" +
//                        "      \"author\": \"Alejandro Sánchez\",\n" +
//                        "      \"title\": \"Tensión en los mercados ante la crisis de Ucrania - Expansión\",\n" +
//                        "      \"description\": \"Transcurridas solo tres semanas del nuevo año, 2022 ya depara un factor &apos;sorpresa&apos; que podría desestabilizar los mercados financieros. La crisis de Ucrania se había recru\",\n" +
//                        "      \"url\": \"https://www.expansion.com/mercados/2022/01/24/61ee7868e5fdea47188b4630.html\",\n" +
//                        "      \"urlToImage\": \"https://phantom-expansion.unidadeditorial.es/1e2ae297aac237e15ab4cf801def9c3a/crop/0x0/2045x1364/resize/1200/f/jpg/assets/multimedia/imagenes/2022/01/24/16430180003792.jpg\",\n" +
//                        "      \"publishedAt\": \"2022-01-24T09:59:21Z\",\n" +
//                        "      \"content\": \"Imagen de un bróker en la Bolsa de Nueva York\\r\\nEFE\\r\\nLos mercados se enfrentan a un factor que a día de hoy supera incluso en la lista de relevancia a la Fed. La crisis de Ucrania abre la puerta a con… [+623 chars]\"\n" +
//                        "    },\n" +
//                        "    {\n" +
//                        "      \"source\": {\n" +
//                        "        \"id\": null,\n" +
//                        "        \"name\": \"Francetvinfo.fr\"\n" +
//                        "      },\n" +
//                        "      \"author\": null,\n" +
//                        "      \"title\": \"VIDEO. Hausse des prix des carburants : \\\"pourquoi pas\\\" de nouvelles mesures déclare Bruno Le Maire, qui exclut - franceinfo\",\n" +
//                        "      \"description\": \"Le ministre de l'Economie a affirmé qu'\\\"on peut envisager des mesures spécifiques pour ceux qui n'ont pas d'autre choix que d'aller travailler avec leur véhicule\\\".\",\n" +
//                        "      \"url\": \"https://www.francetvinfo.fr/economie/transports/prix-des-carburants/baisser-la-fiscalite-sur-les-carburants-je-ferme-la-porte-parce-que-les-francais-n-en-verront-pas-la-couleur-declare-bruno-le-maire-sur-franceinfo_4927867.html\",\n" +
//                        "      \"urlToImage\": \"https://www.francetvinfo.fr/pictures/YDMxC1A1AwzargVfT5KHuZ5yHsc/1500x843/2022/01/24/phpFLTvgJ.jpg\",\n" +
//                        "      \"publishedAt\": \"2022-01-24T09:56:19Z\",\n" +
//                        "      \"content\": \"Le ministre de l'Economie a affirmé qu'\\\"on peut envisager des mesures spécifiques pour ceux qui n'ont pas d'autre choix que d'aller travailler avec leur véhicule\\\".Face à la hausse des prix des carbur… [+3198 chars]\"\n" +
//                        "    },\n" +
//                        "    {\n" +
//                        "      \"source\": {\n" +
//                        "        \"id\": \"google-news\",\n" +
//                        "        \"name\": \"Google News\"\n" +
//                        "      },\n" +
//                        "      \"author\": null,\n" +
//                        "      \"title\": \"Mercado global de La Comida Texturants con panorama competitivo durante el período de pronóstico 2022-2031 – Influencers Web - Influencers Web\",\n" +
//                        "      \"description\": null,\n" +
//                        "      \"url\": \"https://news.google.com/__i/rss/rd/articles/CBMiZ2h0dHBzOi8vd3d3LmluZmx1ZW5jZXJzd2ViLmNvbS9tZXJjYWRvLWdsb2JhbC1kZS1sYS1jb21pZGEtdGV4dHVyYW50cy1wZXJzcGVjdGl2YS1kZS1sYS1pbmR1c3RyaWEtMjAyMi_SAQA?oc=5\",\n" +
//                        "      \"urlToImage\": null,\n" +
//                        "      \"publishedAt\": \"2022-01-24T09:55:17Z\",\n" +
//                        "      \"content\": null\n" +
//                        "    }\n" +
//                        "  ]\n" +
//                        "}"
//            )
//        )
        newsUseCases.getHeadlinesUseCase.invoke(searchQuery, page).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    if (result.data?.totalResults?.coerceAtLeast(0) == 0) {
                        _state.value = HeadlinesState(noContent = searchQuery)
                    } else {
                        _state.value = HeadlinesState(result = result.data)
                    }
                }
                is Resource.Loading -> {
                    _state.value = HeadlinesState(loading = true)
                }
                else -> {
                    _state.value = HeadlinesState(error = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getEverything(page: Int) {
        newsUseCases.getEverythingUseCase.invoke(searchQuery, page).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    if (result.data?.totalResults?.coerceAtLeast(0) == 0) {
                        _state.value = HeadlinesState(noContent = searchQuery)
                    } else {
                        _state.value = HeadlinesState(result = result.data)
                    }
                }
                is Resource.Loading -> {
                    _state.value = HeadlinesState(loading = true)
                }
                else -> {
                    _state.value = HeadlinesState(error = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun saveArticleToDb(article: Article) {
        newsUseCases.saveArticleUseCase.invoke(article).launchIn(viewModelScope)
    }

    fun deleteArticleFromDb(article: Article) {
        newsUseCases.deleteArticleUseCase.invoke(article).launchIn(viewModelScope)
    }

    fun getSavedNews() {
        viewModelScope.launch {
            newsUseCases.getSavedNewsUseCase.invoke().collectLatest {
                _state.value = HeadlinesState(result = NewsRequest(articles = it, "", it.size))
            }
        }
    }
}

fun toNews(json: String): NewsRequest {
    val notesType = object : TypeToken<NewsRequest>() {}.type
    return Gson().fromJson(json, notesType)
}

data class HeadlinesState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val noContent: String = "",
    val result: NewsRequest? = null,
)