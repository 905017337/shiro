�}�V    �3SdK_�/�v
  ��r���7�*((�iD����E�չ��{���GY�P�X��V%�㯿!Q�it\�n��d%A7>f�'��[nV��8�a.�z��$�ROG\{��`mP)"Bs�������*��0{oN�D�ƇC|��9��B9�]E}����1���AKvPҴ���V��;��S�c��*/?�������kg~�9p�-Y���c���&fգ�i؇��8U��݅О�ǔ&</�f��P��怇��	3��Xi���U$�rk�R�*J��k�Z��p^�x�Zx_���yS@&(�a]ô������?�fڲ��F�z�>n9V��~_!��>SvC��n{J�g��(9`��1q�
�Z�6�G���\a+&�Ƈ^�y��H�w$M�rִ]e�!F��ʣ�s��/G�����%�ߎ�ج���t�RT�|��o���ei@IU��4���]k�gr�e�aa��>���֧�#�s���� void contextLoads() {
        String md5 = this.MD5Pwd("czh", "123");
        System.out.println("md5:"+md5);

    }

    public static String MD5Pwd(String username, String pwd) {
        // 加密算法MD5
        // salt盐 username + salt
        // 迭代次数
        String md5Pwd = new SimpleHash("MD5", pwd,
                ByteSource.Util.bytes(username + "salt"), 2).toHex();
        return md5Pwd;
    }


}
