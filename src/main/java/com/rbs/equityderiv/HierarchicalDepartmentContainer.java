package com.rbs.equityderiv;

import org.springframework.transaction.annotation.Transactional;

import com.rbs.equityderiv.domain.Department;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingLocalEntityProvider;

@Transactional
public class HierarchicalDepartmentContainer extends JPAContainer<Department> {

    public HierarchicalDepartmentContainer() {
        super(Department.class);
        setEntityProvider(new CachingLocalEntityProvider<Department>(
                Department.class,
                JPAContainerFactory
                        .createEntityManagerForPersistenceUnit(MyVaadinApplication.PERSISTENCE_UNIT)));
        setParentProperty("parent");
    }

    @Override
    public boolean areChildrenAllowed(Object itemId) {
        return super.areChildrenAllowed(itemId)
                && getItem(itemId).getEntity().isSuperDepartment();
    }
}