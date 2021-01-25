package com.syswarp.views.muelles;

import java.util.Optional;

import com.syswarp.data.entity.Muelles;
import com.syswarp.data.service.MuellesService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import com.syswarp.views.main.MainView;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.converter.StringToIntegerConverter;

@Route(value = "muelles", layout = MainView.class)
@PageTitle("Muelles")
@CssImport("./styles/views/muelles/muelles-view.css")
public class MuellesView extends Div {

    private Grid<Muelles> grid = new Grid<>(Muelles.class, false);

    private TextField id;
    private TextField muelle;
    private TextField idpuerto;
    private TextField kilometro;
    private TextField emax;
    private TextField rv;
    private TextField estructura;
    private TextField det;
    private TextField latitud;
    private TextField longitud;

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private BeanValidationBinder<Muelles> binder;

    private Muelles muelles;

    public MuellesView(@Autowired MuellesService muellesService) {
        setId("muelles-view");
        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("id").setAutoWidth(true);
        grid.addColumn("muelle").setAutoWidth(true);
        grid.addColumn("idpuerto").setAutoWidth(true);
        grid.addColumn("kilometro").setAutoWidth(true);
        grid.addColumn("emax").setAutoWidth(true);
        grid.addColumn("rv").setAutoWidth(true);
        grid.addColumn("estructura").setAutoWidth(true);
        grid.addColumn("det").setAutoWidth(true);
        grid.addColumn("latitud").setAutoWidth(true);
        grid.addColumn("longitud").setAutoWidth(true);
        grid.setItems(query -> muellesService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                Optional<Muelles> muellesFromBackend = muellesService.get(event.getValue().getId());
                // when a row is selected but the data is no longer available, refresh grid
                if (muellesFromBackend.isPresent()) {
                    populateForm(muellesFromBackend.get());
                } else {
                    refreshGrid();
                }
            } else {
                clearForm();
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(Muelles.class);

        // Bind fields. This where you'd define e.g. validation rules
        binder.forField(id).withConverter(new StringToIntegerConverter("Only numbers are allowed")).bind("id");
        binder.forField(muelle).withConverter(new StringToIntegerConverter("Only numbers are allowed")).bind("muelle");
        binder.forField(idpuerto).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("idpuerto");
        binder.forField(kilometro).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("kilometro");
        binder.forField(rv).withConverter(new StringToIntegerConverter("Only numbers are allowed")).bind("rv");
        binder.forField(det).withConverter(new StringToIntegerConverter("Only numbers are allowed")).bind("det");
        binder.forField(latitud).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("latitud");
        binder.forField(longitud).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("longitud");

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.muelles == null) {
                    this.muelles = new Muelles();
                }
                binder.writeBean(this.muelles);

                muellesService.update(this.muelles);
                clearForm();
                refreshGrid();
                Notification.show("Muelles details stored.");
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the muelles details.");
            }
        });

    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setId("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setId("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        id = new TextField("Id");
        muelle = new TextField("Muelle");
        idpuerto = new TextField("Idpuerto");
        kilometro = new TextField("Kilometro");
        emax = new TextField("Emax");
        rv = new TextField("Rv");
        estructura = new TextField("Estructura");
        det = new TextField("Det");
        latitud = new TextField("Latitud");
        longitud = new TextField("Longitud");
        Component[] fields = new Component[]{id, muelle, idpuerto, kilometro, emax, rv, estructura, det, latitud,
                longitud};

        for (Component field : fields) {
            ((HasStyle) field).addClassName("full-width");
        }
        formLayout.add(fields);
        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setId("button-layout");
        buttonLayout.setWidthFull();
        buttonLayout.setSpacing(true);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getLazyDataView().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Muelles value) {
        this.muelles = value;
        binder.readBean(this.muelles);

    }
}
