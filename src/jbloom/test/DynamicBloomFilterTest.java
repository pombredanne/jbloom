package jbloom.test;

import jbloom.core.DynamicBloomFilter;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Sam on 4/13/2017.
 */
public class DynamicBloomFilterTest extends TestCase {

    @Test
    public void testAdd() throws Exception {
        DynamicBloomFilter dbf = new DynamicBloomFilter(100,1000000,0.001);
        for(int i = 0; i < 1000; i++){
            dbf.add(Integer.valueOf(i).toString());
        }
        for(int i = 0; i < 1000; i++){
            assertTrue(dbf.has(Integer.valueOf(i).toString()));
        }
    }

    @Test
    public void testUnion() throws Exception {
        DynamicBloomFilter dbf1 = new DynamicBloomFilter(100,1000000,0.001);
        DynamicBloomFilter dbf2 = new DynamicBloomFilter(100,1000000,0.001);
        for(int i = 0; i < 1000; i++){
            dbf1.add(Integer.valueOf(i).toString());
            dbf1.add(Integer.valueOf(i + 1000/2).toString());
        }
        for(int i = 0; i < 1500; i++){
            assertTrue(dbf1.union(dbf2).has(Integer.valueOf(i).toString()));
        }
    }

    @Test
    public void testIntersection() throws Exception {
        DynamicBloomFilter dbf1 = new DynamicBloomFilter(100,1000000,0.001);
        DynamicBloomFilter dbf2 = new DynamicBloomFilter(100,1000000,0.001);
        for(int i = 0; i < 1000; i++){
            dbf1.add(Integer.valueOf(i).toString());
            dbf2.add(Integer.valueOf(i + 500).toString());
        }
        for(int i = 500; i < 1000; i++){
            assertTrue(dbf1.intersection(dbf2).has(Integer.valueOf(i).toString()));
        }
    }

    @Test
    public void testToString() throws Exception {
        DynamicBloomFilter dbf = new DynamicBloomFilter(100,1000000,0.001);
        for(int i = 0; i < 1000; i++){
            dbf.add(Integer.valueOf(i).toString());
        }
        String test = dbf.toString();

        dbf = DynamicBloomFilter.fromString(test);
        for(int i = 0; i < 1000; i++){
            assertTrue(dbf.has(Integer.valueOf(i).toString()));
        }
    }

    @Test
    public void testFromString() throws Exception {
        //test reading a dbf in from python
        DynamicBloomFilter dbf = DynamicBloomFilter.fromString("100,1000000,0.001,1.00050028373e-07:24:140:100:100:little:e48abff5c3f4d4e318284c5a6cd31b9717c4107772fda049caaded6f090b8efbb40c1fdd1073926c848a1ad806f8abdc17445b5e05b5a7e046be580b949e922fecbc7aa506d555812be3abc2655e533fe72f07954a1f0cec78944294aa99c08fbeaa6d5685aed2913999babe1558d8ffba2201f2c218a25b70b9dcd6298d857952db460b6d5dafd078db119dc9fb050a2199e690d9f6793563b827db6a8e8bb2b0c0675d42def47d336eec6e764a2a877a0a2ba498feb1cfae50c2fe76f79a4b6d04db3fd4d70df92b5c3b12057386e047ba8f539fb64dc7eb95e12b453f03704aa2f0c1493e752e1486dbb6faf4febe7815e7008a99b7533b56619bc121be47e18656e7065e6281e5a0f8438fab6b3594496d06329b1f4dffd8e8d1369523a8e69c0dce1efd943e05886f79e86add77c48a455a53fd5b43e7a12bc3d51176825a25991b3f8877df34dccc2177cbd4e945d9b43cbc149066136718e4d7830a93ee0a6ae0e00c20e7526e8849e57bdf2f551ab911833359d6a917cb69adc295eaee320b3297a9d9665a9ea4e6f6ff8d9e705d02ad9b17c06c8223ff00fa4c0eaa6cb6f8ee|1.00050028373e-07:24:140:100:100:little:28d5386149d53ead2bd71d0101b53765edf5961a5f1e31a967c5cf34b610816275b942913f8cf1f97bc1c07eeefb9d22d05c32d057ea2d09bec2ede23fd1a38ca6552d4536468912620536f4b9885b1fbf3762c89619f7f8696d858528115aec197122cbb21744766e16a96e472afa5e712c1ca8f708be2d38a00c1079977cde50114beed482f0766c7bb4e91e0291df757558441eeaa9dabe2d42e8b35dd5a8123e5ed465fbb841fe02edcea58cc448e45e1ffa0f540327b11ca11b4f9eeb50f6c7480c8ddfe58776da1b035d41211eef0dec90e4fd25b1a7b8d869b9a7a27c674b58fc0b017a2c78bc03445c5af7de5dd42eabbb43ffd6065d7228e105044ac934b982b7ed8360ef7afd70e1534a635b5023f52127dd20cb768f4a7eff1fc50b974f494af112008d96a759adcfdf482ba3d2d1ccbb46528c81f6b6fc488ccc725059ac0aa32a731f115ee53e158d3fd6a40e9705c91a1f3d86b1d3e192b579aa71b771aab72261492b9a9b071dcde345b839e27713f920abf888799749243fff2d4815704a984c97f0c4c8f60b1e9fb56b67c78ec66be73020326fcb2b183fa1494ceb|1.00050028373e-07:24:140:100:100:little:d8438a3ef74edc2355dc86906161e190fff678fe13bc4a04803f9b211b5e369c80c8852c2a7d664094b7bde841c418da5ae54d6f75946e9cd35f90d6642b416db8a60652a3a2235b9e38720fd851939cbb4a5894feea8613674a1300584f6cee1e71afeb325310667d48095bd229af78931590133b67d0f3d38ee9621ef7622e7939d7ecec03e473a50e246080297fd6badb3937021dbad6c10c7cf7fa31d7cd90e6166c571ee5984eab2497ebab83503265aa2ef182352783f776f3e2c8a92b335ff7e2d75dab7ed4aaba6450037dd3b84227dda32d79f10857b535264f1ec68e2d03e37d73a2c141d71f749dca978239cabf4aee042d9ce7fd279476b5327c735ec1ea40abe5cb7032a24588f1eb9ea71477fcf05e5f62b53279435a9fa249808cca17fd53d1a9901da8f914b5a81d8ccd4677adec0060f42fb6c6ef4f8585aba49c72be361ed14cf6f2b41263ebb232aff6989c78f27bf5233f109af9f414a1aa1e735ffa265f46f650f1319f91714372d5213176e6118c413ba6270f45a4f305a7e2a0b3f89d5a1c3dc6566c9e18eec669aa9726f07523b55cfdfd45d6b117cffb90|1.00050028373e-07:24:140:100:100:little:8348d98e125cb74e5c09db78f39661f9deca05f44c50bbee41ec32fb95ba832021b8a013665bbed6b30142f9a2f4e222eb0fe856b1915d980df7b4f3e71bbb0277a426601a4416d4aea4492df246945d908c7b3cc2c9debb225a026b0d3114e15ad5bb528bf973e5fb6dc63750b2e35d9f26dfe753a9640723ec4e09d4d96c4ece93b6c09d86041b30d7edc62dcc39093e4b11fb868b44f541b64e964befbe35f4270ee0dbb1441f6ff7a4bf3752c303d302a6b2627eba7999eea3dad5d9616ad988bb04838df46486f06d793289cf156f96aebaad29e966741c8a191a698d4091bfc7cff74014351785bdbfe8cdda2622376997997ba561e5954eab272728804e4ad7bfaf9d40b8c4cf9f0a69c7a8c87f31276aaaea99ffd94aa1d132c08ae793d1ebb7769b3e3f86189e011ffc72a731325e58663664b5bc0f89cd82aa361dbe7200c7df257679740e61b2422983108724358d3a62ee70fdb683657c67680ef87c1ee7950277ba34204f9932bb645226a258cdf33c9ae2991cea5b7c450ae3302e0aab9327200dca7deeffcab9fa56c20774b741b3615c13e1a78e4d140392df0a0b8e|1.00050028373e-07:24:140:100:100:little:c76d36d5923c580469f0274edd76249c5a7f85d3124b5f9e0701deb7c57d202a411933f614e71a6e70fe6c02df1683c8d57f160891c59d582c7557feb62bf3d996807f60ec455cef0752f131b23237d2e7cc6d1305a1a86727a45fe548ac17be90f0b52a15bf07390f6bc6f2e9b14bf27ee530078744910795ac41a2b6bf6e3238c799e466685bb455b27586f85b15d6537f77593356514c91f68f88276820a5a5db335a7da919f32617d2a05d9bb9ff6eff5609b6e04656e01086350bcdda3a1e96c0733d37b42f0c2867f7d457bd605b3c3b6de11cbbdd00ea452234f7b16bb27e186e9f5452792ea39d9391395d2b7dcc0051688935f33236690096936e1033b6ae6bfdc09b53de444cc57435f5fa4673c9e741b74ff4425fe4f9ba6d96632c24b014c793f735a6d6e8456309168f467c2e5fab52ba73c5a7cdfdd32dd268d922ecb4942fe743c81adeb9c7db41b7cb8363584bad6ee77fd28898ef0d5b0b40849b9bb99f8828cf40554aaa99aa272dc23d70f9a22a4beb2554529efa8c3e554d36ab87ad50fc0900b11fa0d298dfcc748a8055715017afb15f8412c5ebd3925c696f|1.00050028373e-07:24:140:100:100:little:a65567c8ae46f885ef6001417fbac7a2a8cc00bbd79adc80e518f7b52e43895b26d9e6d6ccc469cb4c8907a78865dfaa77992945ae1b8913a9355ca810fbf8afcb2d1ea4f56eeabb0efeaa07de511a5923dd6b0907a436d4891624414c65e429ffb26e52f7cd67b0f44d65086abe293b70467ec9eb347cbf9d68ba66fd7373a9d8c0a21557a5e1869e5da1f23af9da699cfb27d1116f40240dc178463d173eba0cdf520f6de9f2d75fd48c1a493c658cf626f33953a63c9189c4ace25946f32bee919f527cdb4cbe5c4191540d4eb4e4ea3dbc3f2cddea0ca66c4fbece0e45021c20a00df7c6dc81be070d6ae5848e191bb88a9aff3c7b9b57866249f2b4c0a4eb1cf1fff9834edd9bc9e2a12f00fb2ebcf829b72a91db33a290a130b3bf4704366d4cbc90eecd41eb0ff70cf1b213dd8ae5b2cd0a65bf6c939754cf1949c1294573a65065ba137a4d9885f2ff74694f3f6ec103fbc206c06192d90dad6b5c95953099f9ec8f62363c21f7d1bf421bd80ac1e4f412863875085fab1c74b89f9abfc28ffcadf60afba07b44ccf90996739ca4c4b3e51af9991711344436a756d803d79d48|1.00050028373e-07:24:140:100:100:little:5b3084111b117482d32eef6a2eff190aa90f807fd378807c46bae76d764725898dff2e44868837b35a6ac509ff27807dc0abc5b1252417bf88c7a9708c38f58bd4b0e0553dbbf871758ad9c4793a3b0596d9222805bb03e3decf1bfc50058f65eaa82a2a5a62cc899bf8a875e1e52660732a47226324c35d876aced969d7ca9370f59ffd0ab1221f72f6060f2429749a7ad258e073585d45d42f94f569d331b1481d77acfb91c92d46896f09f5e71787ae2f96b247ee90ac3df13f2a2689b294c007da7df8705525b0ca9458c8e3bbf69c702d28ef0fcf835a81a3684018140a7d4d54ec6e7457482ff9b8026297131b906498a3c82ba596abb45a1facce4576af103be167da7b11239965d5b631097eba1f918d7efb8286c66128367f4d9b4d2f7ae80d10e2fc892a3dac8d406e22546c965c1e13e178cfd4d6d5b035075aa47bfe8d2381eea0a7fdcba04aab2f2f8f89fe3e71f1e007d262226ca0c6f38ef3c91bf7f076cdc5e9b1615bdd885908d6d73b5b2e3b0c257cd9fa48770d01fb65e868cb25d01c5fb7d8b51884ef069f149221572d73090d898b04e1eb057b5eeafeba81f3|1.00050028373e-07:24:140:100:100:little:a9902d7bef31db8d1f5eba0c4ba61a592ffc6b9c198465be41de662ef43ef43068daa574c4ce387eac7e996f612529c0287cb9dcad95c7c7ac5210dbd53e8895f9fda3d46a06986ba6440fa543bc640c0c2c9713d6b1dc371aaa0b92e1438f485d3417c652d29a7bcebb268b927f450b42cd29fb1f547e22aed4f1c1976745bd29ccce5b2d41ea3810de95d0019566da48b977cf5de1244eceadc261679a3738b8b3208688c3ddd5a522dbd3f8faba3b50256c13b73ec7e1cff41f50089aad2aafde1b5d475fc788651b0505708c389794ae01767d980c2ad3a039fcae29c2fc5ebd87f4a4984dccedc0244cffef12e13c8b7c6e955dc81bba5dacad6010e2f73167539906ff7ad247fc4e0fb987c17f6648ec985d4c683d274b8f6b5ccb67c533f253112256b35b28a80b30670bb7113e968d4d12b31decc0dc97a3f8318f247a05a52b85003d6af0f759e092a41dd509f2fd38ab57b28973059ecc398a79c2e1ebc9bf82222f22333dc83abd1c168907b066abc32ebc519aa2f1bce2caec3d245f3833af9809efb5ab00d53a45312dc0a06fbdbbce8e678f1e6929ed60bcbc2eb636e9|1.00050028373e-07:24:140:100:100:little:3d7d9ce47504ee7bd1b99e38fb483f90d249763f4fc1cb0490edc3ef3395ce77c1a812b79bf4c3390b6877ccab530a634aad9194f3769c480d22b843d0c93e37b69eec9ed955cf88bc11a86ba4f8de188ca7b5123ec1ff6b67b85979187b631a2bf8ff7c46ef908cc1a5927763626476d0e48e05bef3a7aca5431b0aaa7bb132ae3a7c07969c506b20e643707e1a791ef226f928a165f72ba4ef0492e39bdf05e52d9d71f458511bf17d4867368b6cfa0250b6619327cc7a55d0eef73fad4760c0eb13c9754d6516ced79bf4db146365a5b65f432b0f5f7da2fca9c38748a1a8d4031f3dd019a7e6241bc76ee5e7c8ac7b62fe1aabf5fbf0fc73c49a63632de345506030dc6b48e5d22cfd8db2a61bba322482ec7ea9b54693da15cf9e3ac1e4d370ff2b4af9a69dfa4639e706780b39e5b368105ddeabf304a469fc6fc29eb85b5c2bef1485105f4d14340bdbfac71807b53ea3c36575f0af0d30aaa8d0f3b15d745d19adba61aab58c5a3d01aec5edd6ef0395c7c3b27346a393d7f3ceb800ee82bb9668b9061c362a48dcf756a755080f7b777f5b76ba0be242a74ed5bda99246004c|1.00050028373e-07:24:140:100:100:little:24c7cf801d4d041414d6e7c3f3cda0bf2c148a33b5b365f9797a25736bac726ef2143d5f9bf9750fa8187d5e4a8163c528666c289a5928abb9fc2563316e5b61fb112912544fe373a03d9b691bafa8fd383a6cff8555928e1145131397f5ae2cf96cf08ecb05a2cb27b9b5f6752834dae2a0342241fcb85c56cc451d5bd8987eec30da6c0c0e7cf0460df2b96bc16bab8ab961408784c95d6dedc93799bae2c147a19e07b93e4948c58e0a9718da7c0526b4b4c65978395c75bbc6c376e4050ffd1f5ab99c24adb3bb904b3b827311bea552e1652039aa2e44f2beb1cd0e437a53715b3ea156b32dd259d66dc43d26aee18951777610a80397917f49685d31437a8acf0fa6d5dcbba2782312dfad591ef3ac1ec06f82ce3d45f77dd87bd856e46b7c9d33668a5aa171892104de9f13be9be12ba1333ebb88805fa6a87ba2a3fbe7482b10cbbe9058ea90b8158da1ea870490a47f276fba589ad464fee20e81350ba9a04fc5fa36d2de707b73e93a57f85204cf62b02e3a64a056e4e01af196f49b464b2592fdb78517130db5dfa47c48b55123f2c650aba1c14dd6d75f8c726b394bcfdf");

        //the python dbf had 0-999 in it, so ours better too
        for(int i = 0; i < 1000; i++){
            assertTrue(dbf.has(Integer.valueOf(i).toString()));
        }
    }
}