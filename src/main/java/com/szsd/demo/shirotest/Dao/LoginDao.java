�}LV  1  �@i��� �v
   �r��h3�Z4���@O���Ϣ)�I��&���g���%^ȃD�0��Ҩ�n��N� �!�==���0�C ��U�^p^�ܛ-��80�6����
��0�Q��q@F�X�r��(\��j�fږ+��A���# �G�]�p]v
��Db�A^|M��q����K�b������	%�J���$�A�=��g4�b�JD^�Lo���y~��/,sfU!l$��o�3�E�Uhq���	�P�5�� ����$�rk�R�*J��k�Z��p^�x�Zx_���yS@&(�a]ô������?�fڲ��F�z�>n9V��~_!��>SvC��n{J�g��(9`��1q�
�Z�6�G���\a+&�Ƈ^�y��H�w$M�rִ]e�!F��ʣ�s��/G�����%�ߎ�ج���t�RT�|��o���ei@IU��4���]k�gr�e�aa��>���֧�#�s����m `sys_users` where username = #{account}")
    String findUsername(String account);

    @Select("select username,password,salt from `sys_users` where username = #{account}")
    User findUserInfo(String userName);

    @Select("select r.role from sys_users u LEFT JOIN sys_users_roles ur on u.id = ur.user_id LEFT JOIN sys_roles r ON ur.role_id = r.id where u.username = #{name} ")
    Set<String> findPermissionsByUser(String name);
}
