package com.ujiuye.pro.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.pro.bean.Attachment;
import com.ujiuye.pro.bean.AttachmentExample;
import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.mapper.AttachmentMapper;
import com.ujiuye.pro.mapper.ProjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class AttachmentServiceImp implements  AttachmentService {
    @Resource
    AttachmentMapper attachmentMapper;

    @Resource
    ProjectMapper projectMapper;

    @Override
    public int countByExample(AttachmentExample example) {
        return attachmentMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(AttachmentExample example) {
        return attachmentMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return attachmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Attachment record) {
        return attachmentMapper.insert(record);
    }

    @Override
    public int insertSelective(Attachment record) {
        return attachmentMapper.insertSelective(record);
    }

    @Override
    public List<Attachment> selectByExample(AttachmentExample example) {
        List<Attachment> attachments = attachmentMapper.selectByExample(example);
        for (Attachment attachment : attachments) {
            Project project = projectMapper.selectByPrimaryKey(attachment.getProFk());
            attachment.setProject(project);
        }
        return attachments;
    }

    @Override
    public Attachment selectByPrimaryKey(Integer id) {
        Attachment attachment = attachmentMapper.selectByPrimaryKey(id);
        Project project = projectMapper.selectByPrimaryKey(attachment.getProFk());
        attachment.setProject(project);
        return attachment;
    }

    @Override
    public int updateByExampleSelective(Attachment record, AttachmentExample example) {
        return attachmentMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Attachment record, AttachmentExample example) {
        return attachmentMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Attachment record) {
        return attachmentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Attachment record) {
        return attachmentMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Attachment> searchAttachment(Integer cid, String keyword, Integer orderby) {

        List<Attachment> attachments = attachmentMapper.searchAttachment(cid,keyword,orderby);
        return attachments;
    }

    public List<Attachment> getAttachmentList() {
        //设置分页，第一个参数是当前页数，第二个参数是一页有几条数据
        PageHelper.startPage(3,2);
        //查询数据库里面的数据
        List<Attachment> attachments = attachmentMapper.selectByExample(new AttachmentExample());
        //页面的详细信息，第一个参数是页面的数据，第二个参数是设置显示的页码有多少个
        PageInfo<Attachment> page = new PageInfo<>(attachments,3);

        System.out.println("总页数："+page.getPages());
        System.out.println("总记录数："+page.getTotal());

        System.out.println("当前页："+page.getPageNum());

        System.out.println("上一页："+page.getPrePage());
        System.out.println("下一页："+page.getNextPage());

        System.out.println("是否有上一页："+page.isHasPreviousPage());
        System.out.println("是否有下一页："+page.isHasNextPage());


        System.out.println("是否为首页："+page.isIsFirstPage());
        System.out.println("是否为末页："+page.isIsLastPage());


        System.out.println("PageSize："+page.getPageSize());
        //可以获取到页面里的数据，既也可以当做返回值
        List<Attachment> list = page.getList();
        for (Attachment attachment : list) {
            System.out.println(attachment);
        }
        //获取页面的页码信息
        int[] nums = page.getNavigatepageNums();

        System.out.println(Arrays.toString(nums));

        return attachments;
    }

}
