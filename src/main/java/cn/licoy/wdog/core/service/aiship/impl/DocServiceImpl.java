package cn.licoy.wdog.core.service.aiship.impl;
    public Doc findDocById(String id) {
        Doc user = this.selectById(id);
        if(user == null){
            return null;
        }
        return user;
    }



    @Override
    public Page<DocVO> getAllDocBySplitPage(FindDocDTO findDocDTO) {
        EntityWrapper<Doc> wrapper = new EntityWrapper<>();
        wrapper.orderBy("create_date",findDocDTO.getAsc());
        Page<Doc> userPage = this.selectPage(new Page<>(findDocDTO.getPage(),findDocDTO.getPageSize()), wrapper);
        Page<DocVO> userVOPage = new Page<>();
            try {
               BeanUtils.copyProperties(userPage,userVOPage);
            } catch (Exception e) {
               e.printStackTrace();
            }
        List<DocVO> userVOS = new ArrayList<>();
        userPage.getRecords().forEach(v->{
            DocVO vo = new DocVO();
            try {
               BeanUtils.copyProperties(v,vo);
            } catch (Exception e) {
               e.printStackTrace();
            }
            userVOS.add(vo);
        });
        userVOPage.setRecords(userVOS);
        return userVOPage;
    }

    @Override
    public void statusChange(String userId, Integer status) {
        Doc user = this.selectById(userId);
        if(user==null){
            throw RequestException.fail("Doc不存在");
        }
        Doc Doc = new Doc();
    }

    @Override
    public void removeDoc(String userId) {
        Doc user = this.selectById(userId);
        if(user==null){
            throw RequestException.fail("Doc不存在！");
        }
        Doc Doc = new Doc();
        try {
            this.deleteById(userId);
        }catch (Exception e){
            throw RequestException.fail("删除失败",e);
        }
    }

    @Override
    public void add(DocAddDTO addDTO) {
        try {
            Doc Doc = new Doc();
            BeanUtils.copyProperties(addDTO,Doc);
            Doc.setCreateDate(new Date());
            this.insert(Doc);
        }catch (Exception e){
            throw RequestException.fail("添加Doc失败",e);
        }
    }

    @Override
    public void update(String id, DocUpdateDTO updateDTO) {
        Doc user = this.selectById(id);
        if(user==null){
            throw RequestException.fail(
                    String.format("更新失败，不存在ID为 %s 的Doc",id));
        }
        try {
            BeanUtils.copyProperties(updateDTO,user);
            this.updateById(user);
        }catch (Exception e){
            throw RequestException.fail("Doc信息更新失败",e);
        }
    }
}